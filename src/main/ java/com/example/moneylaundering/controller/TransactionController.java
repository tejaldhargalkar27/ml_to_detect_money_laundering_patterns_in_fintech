package com.example.moneylaundering.controller;

import com.example.moneylaundering.model.Transaction;
import com.example.moneylaundering.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() throws IOException {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable String id) throws IOException {
        return transactionService.findById(id).orElse(null);
    }

    @PostMapping
    public void createTransaction(@RequestBody Transaction transaction) throws IOException {
        transactionService.save(transaction);
    }

    @PutMapping("/{id}")
    public void updateTransaction(@PathVariable String id, @RequestBody Transaction transaction) throws IOException {
        transaction.setTransactionId(id);
        transactionService.update(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable String id) throws IOException {
        transactionService.delete(id);
    }
}
