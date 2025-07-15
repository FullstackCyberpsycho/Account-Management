package org.example.model;

import org.example.dao.UsersAccDao;
import org.example.services.UsersServiceImpl;

public class User {
    private String login, password;
    private int id;
    private UsersServiceImpl usersService = new UsersServiceImpl(new UsersAccDao());

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public int getId(String login) {
        return usersService.getId(login);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }
}
