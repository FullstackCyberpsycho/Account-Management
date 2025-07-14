package org.example.model;

public enum MenuOption {
    LIST_ACCOUNTS("1"),
    ADD_ACCOUNTS("2"),
    CHANGE_PASSWORD("3"),
    DELETE_ACCOUNT("4"),
    EXIT("5");

    private final String code;

    MenuOption(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MenuOption fromCode(String code) {
        for (MenuOption option : values()) {
            if (option.code.equals(code)) {
                return option;
            }
        }
        return null;
    }
}
