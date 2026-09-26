package com.example.bankaccountservice.web;

import com.example.bankaccountservice.dtos.BankAccountRequestDto;
import com.example.bankaccountservice.dtos.BankAccountResponseDTO;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.mappers.AccountMapper;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import com.example.bankaccountservice.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private final BankAccountRepository bankAccountRepository;
    private  final BankAccountService bankAccountService;
    private final AccountMapper accountMapper;
    public AccountRestController(BankAccountRepository bankAccountRepository, BankAccountService bankAccountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountService = bankAccountService;
        this.accountMapper = accountMapper;
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
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDto requestDto) {
        return bankAccountService.addAccount(requestDto);
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
