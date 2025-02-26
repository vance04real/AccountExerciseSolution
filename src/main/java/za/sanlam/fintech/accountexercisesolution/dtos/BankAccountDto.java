package za.sanlam.fintech.accountexercisesolution.dtos;

import lombok.Getter;
import lombok.Setter;
import za.sanlam.fintech.accountexercisesolution.domain.WithdrawalResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BankAccountDto {

    private Long id;
    private BigDecimal balance;
    private LocalDateTime updatedAt;

    public BankAccountDto() {
    }

    public BankAccountDto(Long id, BigDecimal balance, LocalDateTime updatedAt) {
        this.id = id;
        this.balance = balance;
        this.updatedAt = updatedAt;
    }

    public WithdrawalResult withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return WithdrawalResult.failed("Withdrawal amount must be positive");
        }

        if (balance.compareTo(amount) < 0) {
            return WithdrawalResult.insufficientFunds("Insufficient funds for withdrawal");
        }

        balance = balance.subtract(amount);
        updatedAt = LocalDateTime.now();
        return WithdrawalResult.successful(amount);
    }
}
