package za.sanlam.fintech.accountexercisesolution.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.sanlam.fintech.accountexercisesolution.domain.WithdrawalResult;
import za.sanlam.fintech.accountexercisesolution.domain.entity.BankAccount;
import za.sanlam.fintech.accountexercisesolution.dtos.BankAccountDto;
import za.sanlam.fintech.accountexercisesolution.event.EventPublisher;
import za.sanlam.fintech.accountexercisesolution.event.WithdrawalEvent;
import za.sanlam.fintech.accountexercisesolution.mappers.BankAccountMapper;
import za.sanlam.fintech.accountexercisesolution.repository.BankAccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    private final EventPublisher eventPublisher;
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;

    public BankAccountServiceImpl(EventPublisher eventPublisher,
                                  BankAccountRepository bankAccountRepository,
                                  BankAccountMapper bankAccountMapper) {
        this.eventPublisher = eventPublisher;
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    @Transactional
    public WithdrawalResult withdraw(Long accountId, BigDecimal amount) {

        if (accountId == null || amount == null) {
            return WithdrawalResult.failed("Account ID and amount are required");
        }

        try {

            Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountId);

            if (optionalBankAccount.isEmpty()) {
                return WithdrawalResult.failed("Account not found");
            }

            BankAccountDto bankAccountDto = bankAccountMapper.convertToDto(optionalBankAccount.get());

            // Process withdrawal in domain model
            WithdrawalResult result = bankAccountDto.withdraw(amount);

            if (result.isSuccessful()) {

                BankAccount bankAccountEntity = bankAccountMapper.convertToEntity(bankAccountDto);
                //todo set bank entity fields before saving to the DB
                //bankAccountEntity.setUpdatedAt(LocalDateTime.now());

                bankAccountRepository.save(bankAccountEntity);

                // Publish event after successful update
                WithdrawalEvent event = new WithdrawalEvent(
                        result.getTransactionId(),
                        result.getWithdrawnAmount(),
                        accountId,
                        result.getStatus().toString()
                );

                eventPublisher.publishWithdrawalEvent(event);
            }

            return result;

        } catch (Exception e) {
            logger.error("Error processing withdrawal for account: {}", accountId, e);
            return WithdrawalResult.failed("Withdrawal processing error: " + e.getMessage());
        }
    }
}
