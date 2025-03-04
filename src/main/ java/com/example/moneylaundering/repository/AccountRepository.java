package com.example.moneylaundering.repository;

import com.example.moneylaundering.model.Account;
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
public class AccountRepository {

    private static final String FILE_PATH = "data/account_table.csv";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Account> findAll() throws IOException {
        return new CsvToBeanBuilder<Account>(new FileReader(FILE_PATH))
                .withType(Account.class)
                .build()
                .parse();
    }

    public Optional<Account> findById(String accountId) throws IOException {
        return findAll().stream()
                .filter(account -> account.getAccountId().equals(accountId))
                .findFirst();
    }

    public void save(Account account) throws IOException {
        List<Account> accounts = findAll();
        accounts.add(account);
        writeAccountsToFile(accounts);
    }

    public void update(Account account) throws IOException {
        List<Account> accounts = findAll().stream()
                .map(existingAccount -> existingAccount.getAccountId().equals(account.getAccountId()) ? account : existingAccount)
                .collect(Collectors.toList());
        writeAccountsToFile(accounts);
    }

    public void delete(String accountId) throws IOException {
        List<Account> accounts = findAll().stream()
                .filter(account -> !account.getAccountId().equals(accountId))
                .collect(Collectors.toList());
        writeAccountsToFile(accounts);
    }

    private void writeAccountsToFile(List<Account> accounts) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            objectMapper.writeValue(writer, accounts);
        }
    }
}
