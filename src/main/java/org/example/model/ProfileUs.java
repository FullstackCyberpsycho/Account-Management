package org.example.model;

import org.example.services.UsersServiceImpl;
import org.example.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class ProfileUs {
    private UsersServiceImpl usersService;
    private String login;
    private String fileName = "src/main/resources/autoEntrance.txt";
    private File autoEntrance = new File(fileName);


    public ProfileUs(UsersServiceImpl usersService, String login) {
        this.usersService = usersService;
        this.login = login;
    }

    public void menu() {
        Scanner in = new Scanner(System.in);

        System.out.print("ваш логин: " + login + "\n" +
             //   "1. Выйти из аккаунта\n" +
                "1. Удалить аккаунт\n" +
                "'Enter' - Продолжить\n" +
                "Ввод: ");
        String choise = in.nextLine();
        if (choise.equals("1")) {
            removingAccount();
        } /*else if (choise.equals("2")) {

        }*/
    }

    /*private void exitFromAccount() {
        try(FileWriter fileWriter = new FileWriter(autoEntrance)) {
            fileWriter.write("");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("Вы вышли из своего аккаунта\n");
        Ui.getUi().run();
    }*/

    private void removingAccount() {
        usersService.deleteUser(usersService.getId(login));
        try(FileWriter fileWriter = new FileWriter(autoEntrance)) {
            fileWriter.write("");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("Ваш аккаунт был удалён");
        Ui.getUi().run();
    }
}
