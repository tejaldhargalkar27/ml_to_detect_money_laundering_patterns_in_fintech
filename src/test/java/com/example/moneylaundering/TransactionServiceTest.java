package com.example.moneylaundering.service;

import com.example.moneylaundering.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService();
    }

    @Test
    void testCreateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("1");
        transaction.setAccountId("123");
        transaction.setTransactionType("DEPOSIT");
        transaction.setAmount(1000.00);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setCounterparty("Counterparty A");
        transaction.setLocation("Location A");

        Transaction createdTransaction = transactionService.createTransaction(transaction);
        assertNotNull(createdTransaction);
        assertEquals("1", createdTransaction.getTransactionId());
    }

    @Test
    void testGetTransactionById() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("1");
        transaction.setAccountId("123");
        transaction.setTransactionType("DEPOSIT");
        transaction.setAmount(1000.00);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setCounterparty("Counterparty A");
        transaction.setLocation("Location A");

        transactionService.createTransaction(transaction);
        Transaction retrievedTransaction = transactionService.getTransactionById("1");
        assertNotNull(retrievedTransaction);
        assertEquals("1", retrievedTransaction.getTransactionId());
    }

    @Test
    void testUpdateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("1");
        transaction.setAccountId("123");
        transaction.setTransactionType("DEPOSIT");
        transaction.setAmount(1000.00);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setCounterparty("Counterparty A");
        transaction.setLocation("Location A");

        transactionService.createTransaction(transaction);
        transaction.setAmount(2000.00);
        Transaction updatedTransaction = transactionService.updateTransaction("1", transaction);
        assertNotNull(updatedTransaction);
        assertEquals(2000.00, updatedTransaction.getAmount());
    }

    @Test
    void testDeleteTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("1");
        transaction.setAccountId("123");
        transaction.setTransactionType("DEPOSIT");
        transaction.setAmount(1000.00);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setCounterparty("Counterparty A");
        transaction.setLocation("Location A");

        transactionService.createTransaction(transaction);
        transactionService.deleteTransaction("1");
        assertNull(transactionService.getTransactionById("1"));
    }
}
