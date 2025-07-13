package org.example.services;

import org.example.dao.UsersAccDao;
import org.example.model.User;
import org.example.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UsersService {
    private UsersAccDao usersAccDao;
    private String fileName = "src/main/resources/isAcc.txt";
    private File isAcc = new File(fileName);

    public UsersService(UsersAccDao usersAccDao) {
        this.usersAccDao = usersAccDao;
    }

    public void addUser(String login, String password) {
        String infoUser = login + "/" + password;
        if (!usersAccDao.getLoginAndPassword().equals(infoUser)) {
            User users = new User(login, password);
            usersAccDao.addUser(users);

            System.out.println("Вы успешно зарегистрированы!");
            new Ui().mainMenu();
        } else {
            System.out.println("Ошибка: такой аккаунт уже существует");
        }
    }

    public void isGo(String login, String password) {
        String infoUser = login + "/" + password;
        //System.out.println(infoUser + ", " + usersAccDao.getLoginAndPassword());
        Scanner sc = null;
        try {
            sc = new Scanner(isAcc);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (usersAccDao.getLoginAndPassword().contains(infoUser)) {
            try(FileWriter fileWriter = new FileWriter(isAcc)) {
                fileWriter.write(login);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Вы вошли в аккаунт");
            String s = "";
            while (sc.hasNext()) {
                s = sc.nextLine();
            }
            if (s.equals(login)) {
                new Ui();
            }
        } else {
            System.out.println("не ок");
            try(FileWriter fileWriter = new FileWriter(isAcc)) {
                fileWriter.write("0");
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getId(String login) {
        return usersAccDao.getId(login);
    }

    public ArrayList<String> getLogin() {
        return usersAccDao.getUserLogin();
    }
}
