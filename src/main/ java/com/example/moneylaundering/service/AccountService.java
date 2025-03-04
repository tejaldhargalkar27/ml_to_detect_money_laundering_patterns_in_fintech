package com.example.moneylaundering.service;

import com.example.moneylaundering.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public Account createAccount(Account account) {
        accounts.add(account);
        return account;
    }

    public Account getAccountById(String id) {
        return accounts.stream()
                .filter(account -> account.getAccountId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Account updateAccount(String id, Account updatedAccount) {
        Account account = getAccountById(id);
        if (account != null) {
            account.setPartyId(updatedAccount.getPartyId());
            account.setAccountType(updatedAccount.getAccountType());
            account.setBalance(updatedAccount.getBalance());
            account.setEntityDeleted(updatedAccount.isEntityDeleted());
        }
        return account;
    }

    public void deleteAccount(String id) {
        accounts.removeIf(account -> account.getAccountId().equals(id));
    }
}
