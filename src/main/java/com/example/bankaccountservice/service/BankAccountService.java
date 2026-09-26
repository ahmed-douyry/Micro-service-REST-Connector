package com.example.bankaccountservice.service;

import com.example.bankaccountservice.dtos.BankAccountRequestDto;
import com.example.bankaccountservice.dtos.BankAccountResponseDTO;

public interface BankAccountService {
     BankAccountResponseDTO addAccount(BankAccountRequestDto bankAccountRequestDto);
}
