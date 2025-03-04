package com.example.moneylaundering.repository;

import com.example.moneylaundering.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private static final String FILE_PATH = "data/transaction_table.csv";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Transaction> findAll() throws IOException {
        return new CsvToBeanBuilder<Transaction>(new FileReader(FILE_PATH))
                .withType(Transaction.class)
                .build()
                .parse();
    }

    public Optional<Transaction> findById(String transactionId) throws IOException {
        return findAll().stream()
                .filter(transaction -> transaction.getTransactionId().equals(transactionId))
                .findFirst();
    }

    public void save(Transaction transaction) throws IOException {
        List<Transaction> transactions = findAll();
        transactions.add(transaction);
        writeTransactionsToFile(transactions);
    }

    public void update(Transaction transaction) throws IOException {
        List<Transaction> transactions = findAll().stream()
                .map(existingTransaction -> existingTransaction.getTransactionId().equals(transaction.getTransactionId()) ? transaction : existingTransaction)
                .collect(Collectors.toList());
        writeTransactionsToFile(transactions);
    }

    public void delete(String transactionId) throws IOException {
        List<Transaction> transactions = findAll().stream()
                .filter(transaction -> !transaction.getTransactionId().equals(transactionId))
                .collect(Collectors.toList());
        writeTransactionsToFile(transactions);
    }

    private void writeTransactionsToFile(List<Transaction> transactions) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            objectMapper.writeValue(writer, transactions);
        }
    }
}
