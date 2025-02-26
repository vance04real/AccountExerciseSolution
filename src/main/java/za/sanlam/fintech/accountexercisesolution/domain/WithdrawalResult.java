package za.sanlam.fintech.accountexercisesolution.domain;

import lombok.Getter;
import lombok.Setter;
import za.sanlam.fintech.accountexercisesolution.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class WithdrawalResult {

    private String transactionId;
    private String message;
    private Status status;
    private BigDecimal withdrawnAmount;
    private LocalDateTime timestamp;
    private final BigDecimal remainingBalance;

    private WithdrawalResult(Status status, String message, BigDecimal withdrawnAmount, BigDecimal remainingBalance) {
        this.status = status;
        this.message = message;
        this.withdrawnAmount = withdrawnAmount;
        this.transactionId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.remainingBalance = remainingBalance;
    }

    public static WithdrawalResult successful(BigDecimal amount, BigDecimal remainingBalance) {
        return new WithdrawalResult(Status.SUCCESSFUL, "Withdrawal successful", amount, remainingBalance);
    }

    public static WithdrawalResult failed(String reason) {
        return new WithdrawalResult(Status.FAILED, reason, BigDecimal.ZERO, null);
    }

    public static WithdrawalResult insufficientFunds(String reason) {
        return new WithdrawalResult(Status.INSUFFICIENT_FUNDS, reason, BigDecimal.ZERO, null);
    }

    public boolean isSuccessful() {
        return status == Status.SUCCESSFUL;
    }
}
