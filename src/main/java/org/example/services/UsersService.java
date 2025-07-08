package org.example.services;

import org.example.dao.UsersAccDao;
import org.example.model.Users;
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
    private File x = new File(fileName);

    public UsersService(UsersAccDao usersAccDao) {
        this.usersAccDao = usersAccDao;
    }

    public void addUser(String login, String password) {
        String infoUser = login + "/" + password;
        boolean isGo = false;
        char isGo2;
        if (usersAccDao.getLoginAndPassword().equals(infoUser)) {
            isGo = true;
        } else {
            isGo = false;
        }
        if (!isGo) {
            Users users = new Users(login, password);
            usersAccDao.addUser(users);
            try(FileWriter fileWriter = new FileWriter(fileName)) {
                fileWriter.write("1");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            new Ui();
        } else {
            System.out.println("Ошибка Email");
            try(FileWriter fileWriter = new FileWriter(fileName)) {
                fileWriter.write("0");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void isGo(String login, String password) {
        String infoUser = login + "/" + password;
        //System.out.println(infoUser + ", " + usersAccDao.getLoginAndPassword());
        Scanner sc = null;
        try {
            sc = new Scanner(x);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (usersAccDao.getLoginAndPassword().contains(infoUser)) {
            try(FileWriter fileWriter = new FileWriter(x)) {
                fileWriter.write("1");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Вы вошли в аккаунт");
            String s = "";
            while (sc.hasNext()) {
                s = sc.nextLine();
            }
            if (s.equals("1")) {
                new Ui();
            }
        } else {
            System.out.println("не ок");
            try(FileWriter fileWriter = new FileWriter(x)) {
                fileWriter.write("0");
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
