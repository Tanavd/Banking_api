package com.bank.banking_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.banking_api.entity.Accounts;

public interface AccountRepository extends JpaRepository<Accounts, Long>{

}
