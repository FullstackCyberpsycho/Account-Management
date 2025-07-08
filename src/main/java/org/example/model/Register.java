package org.example.model;

import org.example.dao.UsersAccDao;
import org.example.services.UsersService;
import org.example.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Register {
    private Scanner in = new Scanner(System.in);
    private UsersService usersService = new UsersService(new UsersAccDao());
    private String fileName = "src/main/resources/isAcc.txt";
    private File isAcc = new File(fileName);

    public Register() {
        menu();
    }

    private void menu() {
        String choise;

        System.out.print("1. Войти в аккаунт\n" +
                "2. Зарегистрироваться\n" +
                "Ввод: ");
        choise = in.nextLine();
        if (choise.equals("1")) {
            /*String s = "";
            try {
                Scanner sc = new Scanner(isAcc);
                while (sc.hasNext()) {
                    s = sc.nextLine();
                } if (s.equals("1")) {
                    //mainMenu();
                    new Ui();
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }*/
            goAccount();
        } else if (choise.equals("2")) {
            regAccount();
        }
    }

    private void goAccount() {
        System.out.print("Введите login: ");
        String login = in.nextLine();
        System.out.print("Введите пароль: ");
        String password = in.nextLine();

        usersService.isGo(login, password);
    }

    private void regAccount() {
        System.out.print("Введите login: ");
        String lgin = in.nextLine();
        System.out.print("Введите пароль: ");
        String password = in.nextLine();

        usersService.addUser(lgin, password);
    }
}