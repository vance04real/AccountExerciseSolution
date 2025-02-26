package za.sanlam.fintech.accountexercisesolution.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.sanlam.fintech.accountexercisesolution.service.BankAccountService;

@RestController
@RequestMapping("api/v1/bank")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
}
