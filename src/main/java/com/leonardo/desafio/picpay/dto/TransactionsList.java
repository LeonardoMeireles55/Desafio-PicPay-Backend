package com.leonardo.desafio.picpay.dto;

import com.leonardo.desafio.picpay.model.Transaction;

import java.util.List;

public record TransactionsList(
        List<Transaction> transactions
) {
}
