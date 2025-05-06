package com.homework.model.entity;

import com.homework.model.enums.TransactionType;
import com.homework.utils.validators.ValidateBalance;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BankAccount {

    private final String accountNumber;             //уникальный номер счета.
    private BigDecimal balance;                     //текущий баланс счета.
    private final User owner;                       //владелец счета.
    private final List<Transaction> transactions;   //история транзакций.

    ValidateBalance validateBalance = new ValidateBalance();

    public BankAccount(String accountNumber, User owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return List.copyOf(transactions); // Возвращаем копию для защиты от изменений
    }

    /**
     * Метод пополнение счета.
     *
     * @param amount сумма транзакции
     */
    public void deposit(BigDecimal amount) {
        validateBalance.checkAmountOnPositiveSum(amount);
        balance = balance.add(amount);
        Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT, null, this);
        addTransaction(transaction);
    }

    /**
     * Метод для снятия средств со счета (с проверкой на достаточность средств).
     *
     * @param amount сумма транзакции
     */
    public void withdraw(BigDecimal amount) {
        validateBalance.checkBalanceCompareToAmountAndOnPositiveSum(balance, amount);
        balance = balance.subtract(amount);
        Transaction transaction = new Transaction(amount, TransactionType.WITHDRAWAL, this, null);
        addTransaction(transaction);
    }

    /**
     * Метод для добавления транзакции в историю.
     *
     * @param transaction передаваемая транзакция.
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}