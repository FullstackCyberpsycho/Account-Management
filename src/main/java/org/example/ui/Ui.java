package org.example.ui;

import org.example.dao.AccountDao;
import org.example.dao.UsersAccDao;
import org.example.model.Account;
import org.example.model.Register;
import org.example.services.AccountService;
//import org.example.services.Register;
import org.example.services.UsersService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Ui {
    private Scanner in = new Scanner(System.in);
    private String choise;
    private AccountService accountService = new AccountService(new AccountDao());
    private UsersService usersService = new UsersService(new UsersAccDao());
    private String fileName = "src/main/resources/isAcc.txt";
    private File isAcc = new File(fileName);
    
    public Ui() {
        String s = null;
        try {
            Scanner sc = new Scanner(isAcc);
            while (sc.hasNext()) {
                s = sc.nextLine();
            }
            if (s.equals("1")) {
                mainMenu();
            } else {
                new Register();
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void mainMenu() {
        while (true) {
            System.out.print("=== Account Management ===\n" +
                    "1. Список аккаунтов\n" +
                    "2. Добавить аккаунт\n" +
                    "3. Изменить пароль аккаунта\n" +
                    "4. Удалить аккаунт\n" +
                    "5. Профиль(beta)\n" +
                    "6. Выход из приложения\n"+
                    "Ввод: ");
            choise = in.nextLine();

            switch (choise) {
                case "1":
                    printListAcc();
                    break;
                case "2":
                    ptintAddAcc();
                    break;
                case "3":
                    printChangeAcc();
                    break;
                case "4":
                    printDeleteAcc();
                    break;
                case "6":
                    System.out.println("Вы вышли");
                    break;
                default:
                    System.out.println("Ошибка!");
                    break;
            }
            if (choise.equals("6")) {
                break;
            }
        }
    }

    private void ptintAddAcc() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите информацию о аккаунте:");
        System.out.print("Название сервиса: ");
        String nameService = sc.nextLine();
        System.out.print("Логин: ");
        String login = sc.nextLine();
        System.out.print("Пароль: ");
        String password = sc.nextLine();

        accountService.addAccount(nameService, login, password);
    }

    private void printListAcc() {
        accountService.printInfo();
        System.out.print("1. Отсортировать по названию сервиса\n" +
                "Ввод: ");
        choise = in.nextLine();
        switch (choise) {
            case "1":
                System.out.print("1. A-Я/Z\n" +
                        "2. Я/Z-A\n" +
                        "Ввод: ");
                choise = in.nextLine();
                if (choise.equals("1")) {
                    accountService.printSortASCName();
                } else if (choise.equals("2")) {
                    accountService.printSortDESCName();
                }
                System.out.print("'Enter'. продолжить: ");
                choise = in.nextLine();
                break;
        }
    }

    private void printChangeAcc() {
        accountService.printAllInfo();
        System.out.print("Введите id сервиса который хотите изменить: ");
        int id = in.nextInt();
        System.out.print("Введите новый пароль: ");
        Scanner scanner = new Scanner(System.in);
        String newPassword = scanner.nextLine();

        accountService.updatePasswordAccount(id, newPassword);
        System.out.println("пароль аккаунта изменен");
    }

    private void printDeleteAcc() {
        System.out.print("1. Удалить всё\n" +
                "2. Удалить по id\n" +
                "Ввод: ");
        choise = in.nextLine();

        switch (choise) {
            case "1":
                accountService.deleteAccount();
                System.out.println("Аккаунты были удалены");
                break;
            case "2":
                accountService.printAllInfo();
                System.out.print("Введите id сервиса который хотите удалить: ");
                int id = in.nextInt();

                System.out.println("Аккаунт быд удален");
                accountService.deleteAccount(id);
                break;
        }
    }
}
