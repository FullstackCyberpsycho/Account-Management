package org.example.model;

public class Account {
    private int id;
    private String nameService, login, password;

    public int getId() {
        return id;
    }

    public Account(){};

    public Account(String nameService, String login, String password) {
        this.nameService = nameService;
        this.login = login;
        this.password = password;
    }

    public String getNameService() {
        return nameService;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
