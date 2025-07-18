package org.example.model;

public enum MenuOption {
    RPOFILE("1"),
    LIST_ACCOUNTS("2"),
    ADD_ACCOUNTS("3"),
    CHANGE_PASSWORD("4"),
    DELETE_ACCOUNT("5"),
    EXIT("6");

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
