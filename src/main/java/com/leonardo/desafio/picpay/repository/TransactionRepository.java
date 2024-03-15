package com.leonardo.desafio.picpay.repository;

import java.util.List;

import com.leonardo.desafio.picpay.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {
    public List<Transaction> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
