package com.example.bankaccountservice.dtos;

import com.example.bankaccountservice.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccountRequestDto {

    private Double balance ;
    private String currency;
    private AccountType accountType;
}
