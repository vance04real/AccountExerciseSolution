package za.sanlam.fintech.accountexercisesolution.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class BankAccount {

    @Id
    private Long id;

    //todo: add the necessary fields to the BankAccount entity including balance and updatedAt
}
