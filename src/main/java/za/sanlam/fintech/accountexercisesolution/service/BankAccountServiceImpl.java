package za.sanlam.fintech.accountexercisesolution.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.sanlam.fintech.accountexercisesolution.domain.WithdrawalResult;
import za.sanlam.fintech.accountexercisesolution.dtos.request.WithdrawalRequest;
import za.sanlam.fintech.accountexercisesolution.event.EventPublisher;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    private final EventPublisher eventPublisher;
    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(EventPublisher eventPublisher, BankAccountRepository bankAccountRepository) {
        this.eventPublisher = eventPublisher;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public WithdrawalResult withdraw(WithdrawalRequest withdrawalRequest) {
        return null;
    }
}
