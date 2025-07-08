package org.example.services;

import org.example.dao.AccountDao;
import org.example.model.Account;

public class AccountService {
    private AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void printInfo() {
        accountDao.getAccounts().stream().forEach(System.out::println);
    }

    public void printAllInfo() {
        accountDao.getAllInfoAccounts().stream().forEach(System.out::println);
    }

    public void printSortASCName() {
        accountDao.getSortASCNameAccounts().stream().forEach(System.out::println);
    };

    public void printSortDESCName() {
        accountDao.getSortDESCNameAccounts().stream().forEach(System.out::println);
    };

    public void addAccount(String nameService, String ligin, String password) {
        Account account = new Account(nameService, ligin, password);
        accountDao.addAccount(account);
        System.out.println(nameService + " был Добавлен");
    }

    public void deleteAccount(int id) {
        accountDao.deleteAccount(id);
    }

    public void deleteAccount() {
        accountDao.deleteAccount();
    }

    public void updatePasswordAccount(int id, String newPassword) {
        accountDao.updatePasswordAccount(id, newPassword);
    }
}
