package org.example.model;

import org.example.dao.UsersAccDao;
import org.example.services.UsersServiceImpl;

import java.util.Scanner;

public class Register {
    private Scanner in = new Scanner(System.in);
    private UsersServiceImpl usersService = new UsersServiceImpl(new UsersAccDao());
    int userId;

    public Register() {
        regAccount();
        //menu();
    }

    /*private void menu() {
        regAccount();
    }

    private void goAccount() {
        System.out.print("Введите login: ");
        String login = in.nextLine();
        System.out.print("Введите пароль: ");
        String password = in.nextLine();

        usersService.isGo(login, password);
    }*/

    private void regAccount() {


        System.out.print("Введите login: ");
        String login = in.nextLine();
        System.out.print("Введите пароль: ");
        String password = in.nextLine();



        userId = usersService.getId(login);
        usersService.addUser(login, password);
    }
}