package za.sanlam.fintech.accountexercisesolution.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawalRequest {

    @NotNull
    private Long accountId;

    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

}
