package com.homework;

import com.homework.exception.BalanceException;
import com.homework.model.entity.BankAccount;
import com.homework.model.entity.Transaction;
import com.homework.model.entity.User;
import com.homework.model.enums.TransactionType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BankAccountTest {
    private User user;
    private BankAccount account;

    @Before
    public void setUp() {
        user = new User("user1", "John Doe");
        account = new BankAccount("ACC123", user);
    }

    @Test
    public void deposit_ShouldIncreaseBalance() {
        account.deposit(new BigDecimal("1000"));
        assertEquals(new BigDecimal("1000"), account.getBalance());
    }

    @Test(expected = BalanceException.class)
    public void deposit_NegativeAmount_ShouldThrowException() {
        account.deposit(new BigDecimal("-100"));
    }

    @Test
    public void withdraw_ShouldDecreaseBalance() {
        account.deposit(new BigDecimal("1000"));
        account.withdraw(new BigDecimal("500"));
        assertEquals(new BigDecimal("500"), account.getBalance());
    }

    @Test(expected = BalanceException.class)
    public void withdraw_InsufficientFunds_ShouldThrowException() {
        account.deposit(new BigDecimal("100"));
        account.withdraw(new BigDecimal("200"));
    }

    @Test
    public void addTransaction_ShouldIncreaseTransactionHistorySize() {
        Transaction transaction = new Transaction(
                new BigDecimal("100"),
                TransactionType.DEPOSIT,
                account,
                null
        );
        account.addTransaction(transaction);
        assertEquals(1, account.getTransactions().size());
    }
}