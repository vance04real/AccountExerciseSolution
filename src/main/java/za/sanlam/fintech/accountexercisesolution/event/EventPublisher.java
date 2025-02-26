package za.sanlam.fintech.accountexercisesolution.event;

public interface EventPublisher {

    void publishWithdrawalEvent(WithdrawalEvent withdrawalEvent);
}
