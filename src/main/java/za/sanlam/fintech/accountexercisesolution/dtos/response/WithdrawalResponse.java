package za.sanlam.fintech.accountexercisesolution.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WithdrawalResponse {

    private String transactionId;
    private String message;
    private String status;
    private BigDecimal balance;
    private LocalDateTime timestamp;

}
