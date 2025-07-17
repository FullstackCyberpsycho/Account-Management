package org.example.services;

import org.example.ui.Ui;

import java.util.List;

public interface UsersService {
    void addUser(String login, String password);
    int getId(String login);
    List<String> getLogin();

    /*default void mainMenu() {
        new Ui().mainMenu();
    }*/
}
