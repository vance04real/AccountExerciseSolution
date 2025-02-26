package za.sanlam.fintech.accountexercisesolution.domain;

import lombok.Getter;
import lombok.Setter;
import za.sanlam.fintech.accountexercisesolution.enums.Status;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class WithdrawalResult {

    private String transactionId;
    private String message;
    private Status status;
    private BigDecimal withdrawnAmount;


    private WithdrawalResult(Status status, String message, BigDecimal withdrawnAmount) {
        this.status = status;
        this.message = message;
        this.withdrawnAmount = withdrawnAmount;
        this.transactionId = UUID.randomUUID().toString();
    }

    public static WithdrawalResult successful(BigDecimal amount) {
        return new WithdrawalResult(Status.SUCCESSFUL, "Withdrawal successful", amount);
    }

    public static WithdrawalResult failed(String reason) {
        return new WithdrawalResult(Status.FAILED, reason, BigDecimal.ZERO);
    }

    public static WithdrawalResult insufficientFunds(String reason) {
        return new WithdrawalResult(Status.INSUFFICIENT_FUNDS, reason, BigDecimal.ZERO);
    }

    public boolean isSuccessful() {
        return status == Status.SUCCESSFUL;
    }
}
