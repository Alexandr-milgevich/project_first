package com.homework.model.entity;

import com.homework.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final String id;                    //уникальный идентификатор транзакции.
    private final BigDecimal amount;            //сумма транзакции.
    private final TransactionType type;       //тип транзакции (`DEPOSIT`, `WITHDRAWAL`, `TRANSFER`).
    private final LocalDateTime date;           //дата выполнения транзакции.
    private final BankAccount sourceAccount;    //источник транзакции (если применимо).
    private final BankAccount targetAccount;    //получатель транзакции (если применимо).

    public Transaction(BigDecimal amount, TransactionType type, BankAccount sourceAccount, BankAccount targetAccount) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now();
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", type='" + type.getDescription() + '\'' +
                ", date=" + date +
                ", sourceAccount=" + (sourceAccount != null ? sourceAccount.getAccountNumber() : "null") +
                ", targetAccount=" + (targetAccount != null ? targetAccount.getAccountNumber() : "null") +
                '}';
    }
}