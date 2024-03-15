package com.leonardo.desafio.picpay.repository;

import com.leonardo.desafio.picpay.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long>{

    public boolean existsByCpf(String cpf);
    
    public boolean existsByEmail(String email);

}
