package com.example.moneylaundering.service;

import com.example.moneylaundering.model.Transaction;
import com.example.moneylaundering.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAll() throws IOException {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(String transactionId) throws IOException {
        return transactionRepository.findById(transactionId);
    }

    public void save(Transaction transaction) throws IOException {
        transactionRepository.save(transaction);
    }

    public void update(Transaction transaction) throws IOException {
        transactionRepository.update(transaction);
    }

    public void delete(String transactionId) throws IOException {
        transactionRepository.delete(transactionId);
    }
}
