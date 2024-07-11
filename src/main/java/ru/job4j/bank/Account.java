package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс, имитирующий банковский аккаунт.
 * Содержит информацию о реквизитах и сумме на балансе счета.
 * @author aleksLiss
 * @version 1.0
 */
public class Account {
    /**
     * Поле, хранящее адрес реквизита.
     */
    private String requisite;
    /**
     * Поле, хранящее количество денег на балансе.
     */
    private double balance;

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    public String getRequisite() {
        return requisite;
    }

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Сравнение производится с учетом поля requisite.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    /**
     * Хэш-код формируется с учетом поля requisite.
     */
    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}