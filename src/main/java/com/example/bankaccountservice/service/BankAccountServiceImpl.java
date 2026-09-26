package com.example.bankaccountservice.service;

import com.example.bankaccountservice.dtos.BankAccountRequestDto;
import com.example.bankaccountservice.dtos.BankAccountResponseDTO;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.mappers.AccountMapper;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDto bankAccountRequestDto) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(bankAccountRequestDto.getBalance())
                .currency(bankAccountRequestDto.getCurrency())
                .createdAt(new Date())
                .build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return  accountMapper.fromBankAccount(savedBankAccount);

    }
}
