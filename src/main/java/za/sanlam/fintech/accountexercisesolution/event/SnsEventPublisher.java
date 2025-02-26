package za.sanlam.fintech.accountexercisesolution.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;
import za.sanlam.fintech.accountexercisesolution.config.AwsProperties;

@Component
public class SnsEventPublisher implements EventPublisher {
    private static final Logger logger = LoggerFactory.getLogger(SnsEventPublisher.class);

    private final SnsClient snsClient;
    private final ObjectMapper objectMapper;
    private final AwsProperties awsProperties;

    public SnsEventPublisher(SnsClient snsClient, ObjectMapper objectMapper, AwsProperties awsProperties) {
        this.snsClient = snsClient;
        this.objectMapper = objectMapper;
        this.awsProperties = awsProperties;
    }

    @Async("eventPublisherExecutor")
    @Retryable(
            retryFor = {SnsException.class, RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    @Override
    public void publishWithdrawalEvent(WithdrawalEvent withdrawalEvent) {

        try {
            String eventJson = objectMapper.writeValueAsString(withdrawalEvent);

            PublishRequest publishRequest = PublishRequest.builder()
                    .message(eventJson)
                    .topicArn(awsProperties.getWithdrawalTopicArn())
                    .build();

            snsClient.publish(publishRequest);

            logger.info("Published withdrawal event: {}", withdrawalEvent.getTransactionId());

        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize event: {}", withdrawalEvent.getTransactionId(), e);
            throw new RuntimeException("Failed to serialize event", e);
        } catch (SnsException e) {
            logger.error("Failed to publish event to SNS: {}", withdrawalEvent.getTransactionId(), e);
            throw e;
        }
    }
}
