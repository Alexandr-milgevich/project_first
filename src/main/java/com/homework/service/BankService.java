package com.homework.service;

import com.homework.model.entity.BankAccount;
import com.homework.model.entity.Transaction;
import com.homework.model.entity.User;
import com.homework.model.enums.TransactionType;
import com.homework.utils.validators.ValidateBalance;

import java.math.BigDecimal;
import java.util.List;

public class BankService {
    ValidateBalance validateBalance = new ValidateBalance();

    /**
     * Метод создает новый счет для пользователя.
     *
     * @param user          пользователь
     * @param accountNumber уникальный номер счета
     * @return счет
     */
    public BankAccount createAccount(User user, String accountNumber) {
        BankAccount account = new BankAccount(accountNumber, user);
        user.addAccount(account);
        return account;
    }

    /**
     * Метод переводит средства между счетами (с проверкой на достаточность средств).
     *
     * @param sourceAccount источник транзакции
     * @param targetAccount получатель транзакции
     * @param amount        сумма транзакции
     */
    public void transfer(BankAccount sourceAccount, BankAccount targetAccount, BigDecimal amount) {
        validateBalance.checkBalanceCompareToAmountAndOnPositiveSum(sourceAccount.getBalance(), amount);
        sourceAccount.withdraw(amount);
        targetAccount.deposit(amount);
        Transaction transaction = new Transaction(amount, TransactionType.TRANSFER, sourceAccount, targetAccount);
        sourceAccount.addTransaction(transaction);
        targetAccount.addTransaction(transaction);
    }

    /**
     * Метод возвращает историю транзакций для указанного счета.
     *
     * @param account счет пользователя
     * @return история транзакций
     */
    public List<Transaction> getTransactionHistory(BankAccount account) {
        return account.getTransactions();
    }

    /**
     * Метод возвращает общий баланс всех счетов пользователя.
     *
     * @param user пользователь
     * @return общий баланс всех счетов
     */
    public BigDecimal getTotalBalance(User user) {
        return user.getAccounts().stream()
                .map(BankAccount::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}