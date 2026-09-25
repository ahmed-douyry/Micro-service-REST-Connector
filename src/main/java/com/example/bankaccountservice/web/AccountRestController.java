package com.example.bankaccountservice.web;

import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private final BankAccountRepository bankAccountRepository;
    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(
                String.format("Account %s not found",id)
        ));
    }
    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody  BankAccount bankAccount) {
        bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id , @RequestBody BankAccount bankAccount){
        BankAccount bankAccount1 = bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(
                String.format("Account %s not found",id)
        ));
        if(bankAccount.getAccountType()!=null)bankAccount1.setAccountType(bankAccount.getAccountType());
        if(bankAccount.getBalance()!=null)bankAccount1.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency()!=null)bankAccount1.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getCreatedAt()!=null)bankAccount1.setCreatedAt(new Date());
        return  bankAccountRepository.save(bankAccount1);

    }
    @DeleteMapping("bankAccounts/{id}")
    public void delete(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}
