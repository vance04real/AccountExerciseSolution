package za.sanlam.fintech.accountexercisesolution.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.sanlam.fintech.accountexercisesolution.domain.WithdrawalResult;
import za.sanlam.fintech.accountexercisesolution.dtos.request.WithdrawalRequest;
import za.sanlam.fintech.accountexercisesolution.dtos.response.WithdrawalResponse;
import za.sanlam.fintech.accountexercisesolution.service.BankAccountService;

@RestController
@RequestMapping("/api/v1/bank")
public class BankAccountController {

    private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/account/withdraw")
    public ResponseEntity<WithdrawalResponse> withdraw(@Validated @RequestBody WithdrawalRequest withdrawalRequest) {
        logger.info("Processing withdrawal request for account {}", withdrawalRequest.getAccountId());

        WithdrawalResult result = bankAccountService.withdraw(
                withdrawalRequest.getAccountId(),
                withdrawalRequest.getAmount());

        WithdrawalResponse response = new WithdrawalResponse(
                result.getStatus().toString(),
                result.getMessage(),
                result.getTransactionId(),
                result.getRemainingBalance(),
                result.getTimestamp()
        );

        return switch (result.getStatus()) {
            case SUCCESSFUL -> ResponseEntity.ok(response);

            case INSUFFICIENT_FUNDS -> ResponseEntity.badRequest().body(response);

            default -> ResponseEntity.internalServerError().body(response);
        };
    }
}
