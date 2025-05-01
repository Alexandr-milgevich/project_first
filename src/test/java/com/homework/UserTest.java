package com.homework;

import com.homework.model.entity.BankAccount;
import com.homework.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {
    private User user;
    private BankAccount account;

    @Before
    public void setUp() {
        user = new User("user1", "John Doe");
        account = new BankAccount("ACC123", user);
    }

    @Test
    public void addAccount_ShouldIncreaseAccountsCount() {
        user.addAccount(account);
        assertEquals(1, user.getAccounts().size());
    }

    @Test
    public void getAccounts_ShouldReturnCopy() {
        user.addAccount(account);
        List<BankAccount> accounts = user.getAccounts();
        accounts.clear();
        assertEquals(1, user.getAccounts().size());
    }
}