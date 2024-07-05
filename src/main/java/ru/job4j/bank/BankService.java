package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    public void deleteUser(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                users.remove(user);
            }
        }
    }

    public void addAccount(String passport, Account account) {
        if (findByPassport(passport) != null) {
            if (!(users.get(findByPassport(passport))).contains(account)) {
                users.get(findByPassport(passport)).add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        if (findByPassport(passport) != null) {
            List<Account> accounts = users.get(findByPassport(passport));
            for (Account account : accounts) {
                if (account.getRequisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }

    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {

        if (findByPassport(sourcePassport) == null
                || findByRequisite(sourcePassport, sourceRequisite) == null
                || amount > findByRequisite(sourcePassport, sourceRequisite).getBalance()
                || findByPassport(destinationPassport) == null
                || findByRequisite(destinationPassport, destinationRequisite) == null) {
            return false;
        }
        findByRequisite(sourcePassport, sourceRequisite)
                .setBalance(findByRequisite(sourcePassport, sourceRequisite).getBalance() - amount);
        findByRequisite(destinationPassport, destinationRequisite)
                .setBalance(findByRequisite(destinationPassport, destinationRequisite).getBalance() + amount);
        return true;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}