package org.example.ui;

import org.example.dao.AccountDao;
import org.example.dao.UsersAccDao;
import org.example.model.Register;
import org.example.services.AccountService;
import org.example.services.UsersService;

import java.util.Scanner;

public class Ui {
    private Scanner in = new Scanner(System.in);
    private String choise, login;
    private AccountService accountService = new AccountService(new AccountDao());
    private UsersService usersService = new UsersService(new UsersAccDao());
    private int userId;

    public void run() {
        System.out.print("Введите ваш логин для входа в аккаунт или введите 1 для регистрации аккаунта\n" +
                "Ввод: ");
        login = in.nextLine();
        if (usersService.getLogin().contains(login)) {
            userId = usersService.getId(login);
            mainMenu();
        } else if (login.equals("1")) {
            new Register();
        } else if (!usersService.getLogin().contains(login)) {
            System.out.print("Такого логина не существует!\n" +
                    "1. Зарегистрироваться\n" +
                    "2. Выход\n" +
                    "Ввод: ");
            choise = in.nextLine();
            if (choise.equals("1")) {
                new Register();
            } else if (choise.equals("2")) {
                return;
            }
        }
    }

    public void mainMenu() {
        while (true) {
            System.out.print("=== Account Management ===\n" +
                    "1. Список аккаунтов\n" +
                    "2. Добавить аккаунт\n" +
                    "3. Изменить пароль аккаунта\n" +
                    "4. Удалить аккаунт\n" +
                    "5. Выход из приложения\n"+
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
                case "5":
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

        if (userId != -1) {
            accountService.addAccount(nameService, login, password, userId);
        } else {
            System.out.println("Ошибка: пользователь не найден.");
        }
    }

    private void printListAcc() {

        if (userId != -1) {
            accountService.printInfo(userId);
        } else {
            System.out.println("Ошибка id");
        }

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
                    if (userId != -1) {
                        accountService.printSortASCName(userId);
                    } else {
                        System.out.println("Ошибка id");
                    }
                } else if (choise.equals("2")) {
                    if (userId != -1) {
                        accountService.printSortDESCName(userId);
                    } else {
                        System.out.println("Ошибка id");
                    }
                }
                System.out.print("'Enter'. продолжить: ");
                choise = in.nextLine();
                break;
        }
    }

    private void printChangeAcc() {
        if (userId != -1) {
            accountService.printAllInfo(userId);
        } else {
            System.out.println("Ошибка id");
        }

        System.out.print("Введите id сервиса пароль которого хотите изменить: ");
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
                if (userId != -1) {
                    accountService.deleteAllAccount(userId);
                } else {
                    System.out.println("Ошибка id");
                }

                System.out.println("Аккаунты были удалены");
                break;
            case "2":
                if (userId != -1) {
                    accountService.printAllInfo(userId);
                } else {
                    System.out.println("Ошибка id");
                }

                System.out.print("Введите id сервиса который хотите удалить: ");
                int id = in.nextInt();

                System.out.println("Аккаунт быд удален");
                accountService.deleteAccount(id);
                break;
        }
    }
}
