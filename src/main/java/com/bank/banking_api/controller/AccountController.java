package com.bank.banking_api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bank.banking_api.dto.AccountDto;
import com.bank.banking_api.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        System.out.println("Received AccountDto: " + accountDto.getAccountholdername());
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountbyId(@PathVariable Long id){
    	AccountDto accountDto = accountService.getAccountById(id);
    	return ResponseEntity.ok(accountDto);
    }
    
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
    	
    	Double amount = request.get("amount");
    	AccountDto accountDto = accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);	
    }
    
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request){
    	Double amount = request.get("amount");
    	AccountDto accountDto = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
    	
    }
    
    @GetMapping("/get")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
    	List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
    	
    }
    
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
    	accountService.deleteAccount(id);
    	return ResponseEntity.ok("Account Deleted");
    }
    
    
    
}
