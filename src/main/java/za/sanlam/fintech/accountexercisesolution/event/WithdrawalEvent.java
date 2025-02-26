package za.sanlam.fintech.accountexercisesolution.event;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class WithdrawalEvent {

    private final BigDecimal amount;
    private final String transactionId;
    private final String status;
    private final Long accountId;
    private final LocalDateTime timestamp;

    public WithdrawalEvent(String transactionId, BigDecimal amount, Long accountId, String status) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.accountId = accountId;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
