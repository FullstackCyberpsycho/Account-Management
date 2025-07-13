package org.example.services;

import org.example.dao.AccountDao;
import org.example.dao.UsersAccDao;
import org.example.model.Account;
import org.example.model.User;

public class AccountService {
    private AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void printInfo(int userId) {
        accountDao.getAccounts(userId).stream().forEach(System.out::println);
    }

    public void printAllInfo(int userId) {
        accountDao.getAllInfoAccounts(userId).stream().forEach(System.out::println);
    }

    public void printSortASCName(int userId) {
        accountDao.getSortASCNameAccounts(userId).stream().forEach(System.out::println);
    };

    public void printSortDESCName(int userId) {
        accountDao.getSortDESCNameAccounts(userId).stream().forEach(System.out::println);
    };

    public void addAccount(String nameService, String login, String password, int userId) {
        Account account = new Account(nameService, login, password);
        UsersService usersService = new UsersService(new UsersAccDao());

        accountDao.addAccount(account, userId);
        System.out.println(nameService + " был Добавлен");
    }

    public void deleteAccount(int id) {
        accountDao.deleteAccount(id);
    }

    public void deleteAllAccount(int userId) {
        accountDao.deleteAllAccount(userId);
    }

    public void updatePasswordAccount(int id, String newPassword) {
        accountDao.updatePasswordAccount(id, newPassword);
    }
}
