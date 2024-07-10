package com.bank.banking_api.dto;

public class AccountDto {

    private Long id;
    private String accountholdername;
    private double balance;

    public AccountDto(Long id, String accountholdername, double balance) {
        this.id = id;
        this.accountholdername = accountholdername;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountholdername() {
        return accountholdername;
    }

    public void setAccountholdername(String accountholdername) {
        this.accountholdername = accountholdername;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
