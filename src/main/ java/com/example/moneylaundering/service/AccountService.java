package com.example.moneylaundering.service;

import com.example.moneylaundering.model.Account;
import com.example.moneylaundering.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll() throws IOException {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(String accountId) throws IOException {
        return accountRepository.findById(accountId);
    }

    public void save(Account account) throws IOException {
        accountRepository.save(account);
    }

    public void update(Account account) throws IOException {
        accountRepository.update(account);
    }

    public void delete(String accountId) throws IOException {
        accountRepository.delete(accountId);
    }
}
