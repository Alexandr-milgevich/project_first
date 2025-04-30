package com.homework;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

// Класс, представляющий транзакцию
public class Transaction {
    private final String id;                    //уникальный идентификатор транзакции.
    private final BigDecimal amount;            //сумма транзакции.
    private final String type;                  //тип транзакции (`DEPOSIT`, `WITHDRAWAL`, `TRANSFER`).
    private final LocalDateTime date;           //дата выполнения транзакции.
    private final BankAccount sourceAccount;    //источник транзакции (если применимо).
    private final BankAccount targetAccount;    //получатель транзакции (если применимо).

    public Transaction(BigDecimal amount, String type, BankAccount sourceAccount, BankAccount targetAccount) {
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
                ", type='" + type + '\'' +
                ", date=" + date +
                ", sourceAccount=" + (sourceAccount != null ? sourceAccount.getAccountNumber() : "null") +
                ", targetAccount=" + (targetAccount != null ? targetAccount.getAccountNumber() : "null") +
                '}';
    }
}
