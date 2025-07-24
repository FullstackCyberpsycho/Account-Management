package org.example.model;

import org.example.services.UsersServiceImpl;
import org.example.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Register {
    private Scanner in = new Scanner(System.in);
    private UsersServiceImpl usersService;

    public Register(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    public int regAccount() {
        System.out.print("Введите login: ");
        String login = in.nextLine();
        System.out.print("Введите пароль: ");
        String password = in.nextLine();

        usersService.addUser(login, password);

        return usersService.getId(login);
    }

    public void loginAccount(String login, int userId) {
            if (usersService.getLogin().contains(login)) {
                try(FileWriter fileWriter = new FileWriter("src/main/resources/isRegAcc")) {
                    fileWriter.write("1");
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
                new AutoLogin().run(login);
            } else {
                throw new UserNotFoundException(login);
            }
    }
}