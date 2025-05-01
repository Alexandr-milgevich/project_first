package com.homework;

import com.homework.exception.BalanceException;
import com.homework.model.entity.BankAccount;
import com.homework.model.entity.User;
import com.homework.service.BankService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BankServiceTest {
    private BankService bankService;
    private User user;
    private BankAccount acc1;
    private BankAccount acc2;

    @Before
    public void setUp() {
        bankService = new BankService();
        user = new User("user1", "John Doe");
        acc1 = bankService.createAccount(user, "ACC123");
        acc2 = bankService.createAccount(user, "ACC456");
    }

    @Test
    public void createAccount_ShouldAddAccountToUser() {
        assertEquals(2, user.getAccounts().size());
    }

    @Test
    public void transfer_ShouldMoveMoneyBetweenAccounts() {
        acc1.deposit(new BigDecimal("1000"));
        bankService.transfer(acc1, acc2, new BigDecimal("500"));

        assertEquals(new BigDecimal("500"), acc1.getBalance());
        assertEquals(new BigDecimal("500"), acc2.getBalance());
    }

    @Test(expected = BalanceException.class)
    public void transfer_InsufficientFunds_ShouldThrowException() {
        acc1.deposit(new BigDecimal("100"));
        bankService.transfer(acc1, acc2, new BigDecimal("200"));
    }

    @Test
    public void getTotalBalance_ShouldReturnSumOfAllAccounts() {
        acc1.deposit(new BigDecimal("300"));
        acc2.deposit(new BigDecimal("500"));
        assertEquals(new BigDecimal("800"), bankService.getTotalBalance(user));
    }
}