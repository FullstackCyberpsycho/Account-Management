package org.example.services;

public interface AccountService {
    void printInfo(int userId);
    void printAllInfo(int userId);
    void printSortASCName(int userId);
    void printSortDESCName(int userId);
    void addAccount(String nameService, String login, String password, int userId);
    void deleteAccount(int userId ,int id);
    void deleteAllAccount(int userId);
    void updatePasswordAccount(int id, int userId, String newPassword);
}
