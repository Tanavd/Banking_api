package com.bank.banking_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Table(name = "accounts")
@Entity
public class Accounts {

	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Accounts(Long id, String accountholdername, double balance) {
		super();
		this.id = id;
		this.accountholdername = accountholdername;
		this.balance = balance;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "account_holder")
	private String accountholdername;
	@Column (name = "balance")
	private double balance;
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
