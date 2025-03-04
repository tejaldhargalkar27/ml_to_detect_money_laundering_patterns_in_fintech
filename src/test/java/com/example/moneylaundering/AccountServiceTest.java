package com.example.moneylaundering.service;

import com.example.moneylaundering.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService();
    }

    @Test
    void testCreateAccount() {
        Account account = new Account();
        account.setAccountId("1");
        account.setPartyId("123");
        account.setAccountType("SAVINGS");
        account.setBalance(5000.00);
        account.setEntityDeleted(false);

        Account createdAccount = accountService.createAccount(account);
        assertNotNull(createdAccount);
        assertEquals("1", createdAccount.getAccountId());
    }

    @Test
    void testGetAccountById() {
        Account account = new Account();
        account.setAccountId("1");
        account.setPartyId("123");
        account.setAccountType("SAVINGS");
        account.setBalance(5000.00);
        account.setEntityDeleted(false);

        accountService.createAccount(account);
        Account retrievedAccount = accountService.getAccountById("1");
        assertNotNull(retrievedAccount);
        assertEquals("1", retrievedAccount.getAccountId());
    }

    @Test
    void testUpdateAccount() {
        Account account = new Account();
        account.setAccountId("1");
        account.setPartyId("123");
        account.setAccountType("SAVINGS");
        account.setBalance(5000.00);
        account.setEntityDeleted(false);

        accountService.createAccount(account);
        account.setBalance(6000.00);
        Account updatedAccount = accountService.updateAccount("1", account);
        assertNotNull(updatedAccount);
        assertEquals(6000.00, updatedAccount.getBalance());
    }

    @Test
    void testDeleteAccount() {
        Account account = new Account();
        account.setAccountId("1");
        account.setPartyId("123");
        account.setAccountType("SAVINGS");
        account.setBalance(5000.00);
        account.setEntityDeleted(false);

        accountService.createAccount(account);
        accountService.deleteAccount("1");
        assertNull(accountService.getAccountById("1"));
    }
}
