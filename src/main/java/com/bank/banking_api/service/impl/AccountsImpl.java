package com.bank.banking_api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bank.banking_api.dto.AccountDto;
import com.bank.banking_api.entity.Accounts;
import com.bank.banking_api.mapper.Accountmapper;
import com.bank.banking_api.repository.AccountRepository;
import com.bank.banking_api.service.AccountService;

@Service
public class AccountsImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Accounts account = Accountmapper.maptoaccount(accountDto);
		Accounts savedAccount = accountRepository.save(account);
		return Accountmapper.maptoaccountdto(savedAccount);
	}

	@Override
	public AccountDto getAccountById( Long id) {
		Accounts account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Donot Exisit"));
		return Accountmapper.maptoaccountdto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Accounts account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Donot Exisit"));
		 double total = account.getBalance()+ amount;
		 account.setBalance(total);
		 Accounts savedAccount= accountRepository.save(account);	     
		return Accountmapper.maptoaccountdto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Accounts account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Donot Exisit"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient amount");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Accounts savedAccount = accountRepository.save(account);
		return Accountmapper.maptoaccountdto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Accounts> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> Accountmapper.maptoaccountdto(account))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		Accounts account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Donot Exisit"));
		
		accountRepository.deleteById(id);
	}

}
