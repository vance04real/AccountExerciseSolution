package za.sanlam.fintech.accountexercisesolution.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.sanlam.fintech.accountexercisesolution.dtos.request.WithdrawalRequest;
import za.sanlam.fintech.accountexercisesolution.dtos.response.WithdrawalResponse;
import za.sanlam.fintech.accountexercisesolution.service.BankAccountService;

@RestController
@RequestMapping("/api/v1/bank")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/account/withdraw")
    public ResponseEntity<WithdrawalResponse> withdraw(@Validated @RequestBody WithdrawalRequest withdrawalRequest) {
        return ResponseEntity.ok(bankAccountService.withdraw(withdrawalRequest));
    }
}
