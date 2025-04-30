package com.homework;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Класс, представляющий банковский счет
public class BankAccount {

    private final String accountNumber;             //уникальный номер счета.
    private BigDecimal balance;                     //текущий баланс счета.
    private final User owner;                       //владелец счета.
    private final List<Transaction> transactions;   //история транзакций.

    Validate validate = new Validate();

    public BankAccount(String accountNumber, User owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public User getOwner() {
        return owner;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions); // Возвращаем копию для защиты от изменений
    }

    /**
     * Метод пополнение счета.
     *
     * @param amount сумма транзакции
     */
    public void deposit(BigDecimal amount) {
        validate.checkAmountOnPositiveSum(amount);
        balance = balance.add(amount);
        Transaction transaction = new Transaction(amount, "DEPOSIT", this, null);
        addTransaction(transaction);
    }

    /**
     * Метод для снятия средств со счета (с проверкой на достаточность средств).
     *
     * @param amount сумма транзакции
     */
    public void withdraw(BigDecimal amount) {
        validate.checkBalanceCompareToAmountAndOnPositiveSum(balance, amount);
        balance = balance.subtract(amount);
        Transaction transaction = new Transaction(amount, "WITHDRAW", this, null);
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
