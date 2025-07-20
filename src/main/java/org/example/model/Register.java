package org.example.model;

import org.example.services.UsersServiceImpl;

import java.util.Scanner;

public class Register {
    private Scanner in = new Scanner(System.in);
    private UsersServiceImpl usersService;
    private int userId;

    public Register(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    public void regAccount() {
        System.out.print("Введите login: ");
        String login = in.nextLine();
        System.out.print("Введите пароль: ");
        String password = in.nextLine();

        userId = usersService.getId(login);
        usersService.addUser(login, password);
    }
}