package com.homework;

import java.util.ArrayList;
import java.util.List;

// Класс, представляющий пользователя банка
public class User {
    private final String id;                    //уникальный идентификатор пользователя.
    private final String name;                  //имя пользователя.
    private final List<BankAccount> accounts;   //список счетов пользователя.

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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
     *
     * @return список счетов пользователя
     */
    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts); // Возвращаем копию для защиты от изменений
    }
}
