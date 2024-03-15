package com.leonardo.desafio.picpay.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Table("transactions")
public record Transaction(
        @Id Long id,
        @NotNull Long payer,
        @NotNull Long payee,
        @NotNull @Positive BigDecimal value,
        @CreatedDate LocalDateTime createdAt) {
    public Transaction {
        value = value.setScale(2);
    }
}
