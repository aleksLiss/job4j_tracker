package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, описывающий работу с пользователями банка, а так же перевод средств.
 * @author aleksLiss
 * @version 1.0
 */
public class BankService {
    /**
     * Хранилище пользователей(экземпляров класса
     * @see User).
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Добавление нового пользователя в банковскую систему.
     * @param user
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Удаление пользователя из банковской системы.
     * @param passport
     */
    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    /**
     * Добалвение нового пользовательского аккаунта в банковскую систему.
     * @param passport
     * @param account
     */
    public void addAccount(String passport, Account account) {
        User userFromDataBase = findByPassport(passport);
        if (userFromDataBase != null) {
             List<Account> userAccounts = users.get(userFromDataBase);
            if (!(userAccounts.contains(account))) {
                userAccounts.add(account);
            }
        }
    }

    /**
     * Поиск пользователя банка по данным паспорта.
     * @param passport
     * @return пользователя банка.
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Поиск пользовательского аккаунта по данным паспорта и
     * и данным реквизитов.
     * @param passport
     * @param requisite
     * @return пользовательский аккаунт.
     */
    public Account findByRequisite(String passport, String requisite) {
        User userFromDataBase = findByPassport(passport);
        if (userFromDataBase != null) {
            List<Account> accounts = users.get(userFromDataBase);
            for (Account account : accounts) {
                if (account.getRequisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Перевод средств между различными акааунтами.
     * Если у аккаунта, с которого осуществляется перевод средств,
     * недостаточная сумма для перевода, то
     * @return false.
     * Если указанные данные паспорта и реквизита аккаунта-плательщика
     * и/или аккаунта-получается недействительны, то
     * @return false.
     * @param sourcePassport данные аккаунта-плательщика.
     * @param sourceRequisite данные аккаунта-плательщика.
     * @param destinationPassport данные аккаунта-получателя.
     * @param destinationRequisite данные аккаунта-получателя.
     * @param amount количество средств для перевода.
     * @return true при успешном выполнении операции.
     */
    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        Account sourceAccount = findByRequisite(sourcePassport, sourceRequisite);
        Account destinationAccount = findByRequisite(destinationPassport, destinationRequisite);
        if (sourceAccount == null
                || amount > sourceAccount.getBalance()
                || destinationAccount == null) {
            return false;
        }
        sourceAccount
                .setBalance(sourceAccount.getBalance() - amount);
        destinationAccount
                .setBalance(destinationAccount.getBalance() + amount);
        return true;
    }

    /**
     * Получение списка аккаунтов пользователя банковской системы.
     * @param user пользователь банковской системы.
     * @return список аккаунтов пользователя банковской системы.
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}