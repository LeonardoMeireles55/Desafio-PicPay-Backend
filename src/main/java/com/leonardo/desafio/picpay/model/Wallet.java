package com.leonardo.desafio.picpay.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("wallets")
public record Wallet(
        @Id Long id,
        String name,
        String cpf,
        String email,
        String password,
        int type,
        BigDecimal balance) {
    public Wallet debitWallet(BigDecimal value) {
        return new Wallet(id, name, cpf, email, password, type, balance.subtract(value));
    }

    public Wallet creditWallet(BigDecimal value) {
        return new Wallet(id, name, cpf, email, password, type, balance.add(value));
    }
}