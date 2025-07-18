package org.example.ui;

import org.example.dao.AccountDao;
import org.example.dao.UsersAccDao;
import org.example.model.*;
import org.example.services.AccountServiceImpl;
import org.example.services.UsersServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ui {
    private Scanner in = new Scanner(System.in);
    private String choise, login;
    private AccountServiceImpl accountService = new AccountServiceImpl(new AccountDao());
    private UsersServiceImpl usersService = new UsersServiceImpl(new UsersAccDao(), new AutoLogin());
    private Register register = new Register(usersService);
    private int userId;
    private static Ui ui;

    private Ui() {}

     public static Ui getUi() {
        if (ui == null) {
            ui = new Ui();
        }
        return ui;
    }

    public void run() {
        String fileName = "src/main/resources/autoEntrance.txt";
        Path path = Path.of(fileName);
        String isAcc;

        try {
            try (Stream<String> stream = Files.lines(path)) {
                isAcc = stream.findFirst().orElse("");
            }
            try (Stream<String> stream = Files.lines(path)) {
                login = stream.skip(1).findFirst().orElse("");
            }

            if (isAcc.equals("1")) {
                userId = usersService.getId(login);
                mainMenu();
            } else {
                System.out.print("1. Войти в аккаунт\n" +
                        "2. Зарегистрироватся\n" +
                        "3. Выход из приложения\n" +
                        "Ввод: ");
                choise = in.nextLine();
                if (choise.equals("1")) {
                    System.out.print("Введите ваш логин: ");
                    login = in.nextLine();

                    new AutoLogin().run(login);

                    if (usersService.getLogin().contains(login)) {
                        userId = usersService.getId(login);
                        mainMenu();
                    } else {
                        throw new UserNotFoundException(login);
                    }
                } else if (choise.equals("2")) {
                    register.regAccount();
                } else if (choise.equals("3")) {
                    System.exit(0);
                }
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.print("1. Зарегистрироваться\n" +
                    "2. Выход\n" +
                    "Ввод: ");
            choise = in.nextLine();
            if (choise.equals("1")) {
                register.regAccount();
            } else if (choise.equals("2")) {
                System.exit(0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void mainMenu() {
        while (true) {
            System.out.print("=== Account Management ===\n" +
                    "1. Профиль\n" +
                    "2. Список аккаунтов\n" +
                    "3. Добавить аккаунт\n" +
                    "4. Изменить пароль аккаунта\n" +
                    "5. Удалить аккаунт\n" +
                    "6. Выход из приложения\n"+
                    "Ввод: ");
            choise = in.nextLine();

            MenuOption option = MenuOption.fromCode(choise);
            if (option == null) {
                System.out.println("Ошибка ввода");
            } else {
                switch (option) {
                    case RPOFILE:
                        new ProfileUs(usersService, login).menu();
                        break;
                    case LIST_ACCOUNTS:
                        printListAcc();
                        break;
                    case ADD_ACCOUNTS:
                        ptintAddAcc();
                        break;
                    case CHANGE_PASSWORD:
                        printChangeAcc();
                        break;
                    case DELETE_ACCOUNT:
                        printDeleteAcc();
                        break;
                    case EXIT:
                        System.out.println("Вы вышли");
                        System.exit(0);
                        break;
                }
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

        accountService.addAccount(nameService, login, password, userId);
    }

    private void printListAcc() {
        accountService.printInfo(userId);

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
                    accountService.printSortASCName(userId);
                } else if (choise.equals("2")) {
                    accountService.printSortDESCName(userId);
                }
                System.out.print("'Enter' - продолжить: ");
                choise = in.nextLine();
                break;
        }
    }

    private void printChangeAcc() {
        accountService.printAllInfo(userId);

        System.out.print("Введите id сервиса пароль которого хотите изменить: ");
        int id = in.nextInt();
        System.out.print("Введите новый пароль: ");
        Scanner scanner = new Scanner(System.in);
        String newPassword = scanner.nextLine();

        accountService.updatePasswordAccount(id, userId, newPassword);
        System.out.println("пароль аккаунта изменен");
    }

    private void printDeleteAcc() {
        System.out.print("1. Удалить всё\n" +
                "2. Удалить по id\n" +
                "Ввод: ");
        choise = in.nextLine();

        switch (choise) {
            case "1":
                accountService.deleteAllAccount(userId);
                System.out.println("Аккаунты были удалены");
                break;
            case "2":
                accountService.printAllInfo(userId);
                System.out.print("Введите id сервиса который хотите удалить: ");

                int id = in.nextInt();
                accountService.deleteAccount(userId,id);
                System.out.println("Аккаунт быд удален");
                break;
        }
    }
}
