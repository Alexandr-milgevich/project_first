package com.homework.model.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String id;                    //уникальный идентификатор пользователя.
    private final String name;                  //имя пользователя.
    private final List<BankAccount> accounts;   //список счетов пользователя.

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    /**
     * Метод добавление нового счета пользователю.
     *
     * @param account счет пользователя.
     */
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    /**
     * Метод возвращает список счетов пользователя.
     * Возвращаем копию для защиты от изменений
     *
     * @return список счетов пользователя
     */
    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts); //
    }
}