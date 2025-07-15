package org.example.model;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String login) {
        super("Пользователь с логином " + login + " не найден");
    }
}
