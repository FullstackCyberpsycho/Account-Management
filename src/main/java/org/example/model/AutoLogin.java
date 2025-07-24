package org.example.model;

import org.example.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AutoLogin {

    public void run(String login) {
        Scanner in = new Scanner(System.in);
        System.out.print("1. Запомнить меня(автовход)\n" +
                "'Enter' - Продолжить\n" +
                "Ввод: ");
        String choise = in.nextLine();
        if (choise.equals("1")) {
            try(FileWriter fileWriter = new FileWriter("src/main/resources/autoEntrance.txt")) {
                fileWriter.write("1\n" + login);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            //Ui.getUi().mainMenu();
        }
    }
}
