package za.sanlam.fintech.accountexercisesolution.service;

import za.sanlam.fintech.accountexercisesolution.domain.WithdrawalResult;
import za.sanlam.fintech.accountexercisesolution.dtos.request.WithdrawalRequest;

public interface BankAccountService {

    WithdrawalResult withdraw(WithdrawalRequest withdrawalRequest);
}

