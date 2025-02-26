package za.sanlam.fintech.accountexercisesolution.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bank.aws")
@Getter
@Setter
public class AwsProperties {

    private String region;
    private String accountId;
    private String withdrawalTopicArn;
}
