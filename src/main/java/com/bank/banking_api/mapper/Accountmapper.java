package com.bank.banking_api.mapper;

import com.bank.banking_api.dto.AccountDto;
import com.bank.banking_api.entity.Accounts;

public class Accountmapper {

    public static Accounts maptoaccount(AccountDto accountDto) {
        System.out.println("Mapping DTO to Entity: " + accountDto.getAccountholdername());
        return new Accounts(
            accountDto.getId(),
            accountDto.getAccountholdername(),
            accountDto.getBalance()
        );
    }

    public static AccountDto maptoaccountdto(Accounts accounts) {
        System.out.println("Mapping Entity to DTO: " + accounts.getAccountholdername());
        return new AccountDto(
            accounts.getId(),
            accounts.getAccountholdername(),
            accounts.getBalance()
        );
    }
}
