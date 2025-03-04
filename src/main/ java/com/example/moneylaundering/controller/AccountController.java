package com.example.moneylaundering.controller;

import com.example.moneylaundering.model.Account;
import com.example.moneylaundering.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() throws IOException {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable String id) throws IOException {
        return accountService.findById(id).orElse(null);
    }

    @PostMapping
    public void createAccount(@RequestBody Account account) throws IOException {
        accountService.save(account);
    }

    @PutMapping("/{id}")
    public void updateAccount(@PathVariable String id, @RequestBody Account account) throws IOException {
        account.setAccountId(id);
        accountService.update(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) throws IOException {
        accountService.delete(id);
    }
}
