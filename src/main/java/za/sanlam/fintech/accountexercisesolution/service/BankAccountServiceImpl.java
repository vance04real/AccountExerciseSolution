package za.sanlam.fintech.accountexercisesolution.service;

import org.springframework.stereotype.Service;
import za.sanlam.fintech.accountexercisesolution.domain.WithdrawalResult;
import za.sanlam.fintech.accountexercisesolution.dtos.request.WithdrawalRequest;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Override
    public WithdrawalResult withdraw(WithdrawalRequest withdrawalRequest) {
        return null;
    }
}
