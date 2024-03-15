package com.leonardo.desafio.picpay.service;

import com.leonardo.desafio.picpay.infra.GlobalErrorHandling;

import com.leonardo.desafio.picpay.dto.TransactionsList;
import com.leonardo.desafio.picpay.enums.WalletType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonardo.desafio.picpay.model.Transaction;
import com.leonardo.desafio.picpay.repository.TransactionRepository;
import com.leonardo.desafio.picpay.repository.WalletRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository,
            AuthorizerService authorizerService, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    public TransactionsList findAllTransactions() {
        return new TransactionsList(
                transactionRepository.findAllByOrderByCreatedAtDesc(Pageable.ofSize(5))
                        .stream().toList());
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        transactionValidation(transaction);

        var newTransaction = transactionRepository.save(transaction);

        var payerWallet = walletRepository.findById(transaction.payer()).get();
        var payeeWallet = walletRepository.findById(transaction.payee()).get();

        walletRepository.save(payeeWallet.creditWallet(transaction.value()));
        walletRepository.save(payerWallet.debitWallet(transaction.value()));

        authorizerService.authorize(transaction);
        notificationService.notify(newTransaction);

        return newTransaction;
    }

    private void transactionValidation(Transaction transaction) {
        if (transaction.payer() == transaction.payee()) {
            throw new GlobalErrorHandling.InvalidTransactionException();
        }

        var payerWallet = walletRepository.findById(transaction.payer());

        if (payerWallet == null || payerWallet.get().type() == WalletType.LOGISTA.getValue()) {
            throw new GlobalErrorHandling.UnauthorizedTransactionException();
        }

        var payee = walletRepository.findById(transaction.payee());

        if (payee == null) {
            throw new GlobalErrorHandling.InvalidTransactionException();
        }

        if (payerWallet.get().balance().compareTo(transaction.value()) <= 0) {
            throw new GlobalErrorHandling.InvalidTransactionException();
        }
    }
}
