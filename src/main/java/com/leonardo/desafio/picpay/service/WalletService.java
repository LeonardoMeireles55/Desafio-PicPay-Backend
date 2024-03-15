package com.leonardo.desafio.picpay.service;

import com.leonardo.desafio.picpay.infra.GlobalErrorHandling;
import com.leonardo.desafio.picpay.model.Wallet;
import com.leonardo.desafio.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(Wallet wallet) {
        if (wallet.cpf() == null || wallet.email() == null) {
            throw new IllegalArgumentException("CPF and email are required");
        }
        if (walletRepository.existsByCpf(wallet.cpf()) || walletRepository.existsByEmail(wallet.email())) {
            throw new GlobalErrorHandling.DataIntegrityViolationException();
        }

        return walletRepository.save(wallet);
    }
}
