package za.sanlam.fintech.accountexercisesolution.mappers;

import za.sanlam.fintech.accountexercisesolution.domain.entity.BankAccount;
import za.sanlam.fintech.accountexercisesolution.dtos.BankAccountDto;

public class BankAccountMapper {

    //todo this class can be implemented using a proper mapper library like MapStruct or Dozer

    public BankAccountDto convertToDto(BankAccount entity) {
        if (entity == null) {
            return null;
        }
        return new BankAccountDto(
                //todo: add the necessary fields to the BankAccountDto
        );
    }

    public BankAccount convertToEntity(BankAccountDto bankAccountDto) {
        if (bankAccountDto == null) {
            return null;
        }

        return new BankAccount(

              //todo: add the necessary fields to the BankAccount entity
        );

    }
}
