package org.example.services;

import org.example.dao.AccountDao;
import org.example.model.Account;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;
    //public UsersServiceImpl usersService;

    public AccountServiceImpl(AccountDao accountDao) {
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
        accountDao.addAccount(account, userId);

        System.out.println(nameService + " был Добавлен");
    }

    public void deleteAccount(int userId ,int id) {
        accountDao.deleteAccount(userId, id);
    }

    public void deleteAllAccount(int userId) {
        accountDao.deleteAllAccount(userId);
    }

    public void updatePasswordAccount(int id, int userId, String newPassword) {
        accountDao.updatePasswordAccount(id, userId, newPassword);
    }
}
