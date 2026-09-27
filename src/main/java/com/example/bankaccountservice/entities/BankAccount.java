package com.example.bankaccountservice.entities;

import com.example.bankaccountservice.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity @NoArgsConstructor @AllArgsConstructor @Builder @Data
public class BankAccount {
    @Id
    private String id ;
    private Date createdAt;
    private Double balance ;
    private String currency;
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;
    @ManyToOne
    private Customer customer ;
}
