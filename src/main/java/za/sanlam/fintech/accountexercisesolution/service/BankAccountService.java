package za.sanlam.fintech.accountexercisesolution.service;

import za.sanlam.fintech.accountexercisesolution.domain.WithdrawalResult;

import java.math.BigDecimal;

public interface BankAccountService {

    WithdrawalResult withdraw(Long accountId, BigDecimal amount);
}

