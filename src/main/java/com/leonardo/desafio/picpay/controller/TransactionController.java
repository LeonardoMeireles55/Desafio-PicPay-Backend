package com.leonardo.desafio.picpay.controller;

import com.leonardo.desafio.picpay.dto.TransactionsList;
import com.leonardo.desafio.picpay.model.Transaction;
import com.leonardo.desafio.picpay.service.TransactionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<TransactionsList> getTransactions() {
        var response = transactionService.findAllTransactions();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        var response = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(response);
    }
}
