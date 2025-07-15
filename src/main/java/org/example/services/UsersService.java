package org.example.services;

import java.util.ArrayList;

public interface UsersService {
    void addUser(String login, String password);
    int getId(String login);
    ArrayList<String> getLogin();
}
