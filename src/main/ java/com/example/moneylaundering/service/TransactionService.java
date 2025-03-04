package com.example.moneylaundering.service;

import com.example.moneylaundering.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public Transaction createTransaction(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    public Transaction getTransactionById(String id) {
        return transactions.stream()
                .filter(transaction -> transaction.getTransactionId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Transaction updateTransaction(String id, Transaction updatedTransaction) {
        Transaction transaction = getTransactionById(id);
        if (transaction != null) {
            transaction.setAccountId(updatedTransaction.getAccountId());
            transaction.setTransactionType(updatedTransaction.getTransactionType());
            transaction.setAmount(updatedTransaction.getAmount());
            transaction.setTimestamp(updatedTransaction.getTimestamp());
            transaction.setCounterparty(updatedTransaction.getCounterparty());
            transaction.setLocation(updatedTransaction.getLocation());
        }
        return transaction;
    }

    public void deleteTransaction(String id) {
        transactions.removeIf(transaction -> transaction.getTransactionId().equals(id));
    }
}
