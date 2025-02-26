package za.sanlam.fintech.accountexercisesolution.dtos.response;

import lombok.Data;

@Data
public class WithdrawalResponse {

    private String transactionId;
    private String message;
    private String status;

}
