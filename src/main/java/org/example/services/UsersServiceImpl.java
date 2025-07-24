package org.example.services;

import org.example.dao.UsersAccDao;
import org.example.model.AutoLogin;
import org.example.model.User;
import org.example.ui.Ui;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class UsersServiceImpl implements UsersService {
    private UsersAccDao usersAccDao;
    private String fileName = "src/main/resources/autoEntrance.txt";
    private File autoEntrance = new File(fileName);
    private AutoLogin autoLogin;

    public UsersServiceImpl(UsersAccDao usersAccDao, AutoLogin autoLogin) {
        this.usersAccDao = usersAccDao;
        this.autoLogin = autoLogin;
    }

    public void addUser(String login, String password) {
        String infoUser = login + "/" + password;
        Scanner in = new Scanner(System.in);

        if (!usersAccDao.getLoginAndPassword().equals(infoUser)) {
            User users = new User(login, password);
            usersAccDao.addUser(users);
            autoLogin.run(login);

            System.out.println("Вы успешно зарегистрированы!");
            Ui.getUi().run();
        } else {
            System.out.println("Ошибка: такой аккаунт уже существует");
        }
    }

    public int getId(String login) {
        return usersAccDao.getId(login);
    }

    public List<String> getLogin() {
        return usersAccDao.getUserLogin();
    }

    public void deleteUser(int userId) {
        usersAccDao.deleteUser(userId);
    }
}
