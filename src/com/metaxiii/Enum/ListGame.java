package com.metaxiii.Enum;

public enum ListGame {
    PlusOuMoins("1"),
    Mastermind("2");

    private String value;

    ListGame(String value) {
        this.value = value;
    }

    public static ListGame getGame(String el) {
        for (ListGame element : ListGame.values()) {
            if (el.equals(element.value))
                return element;
        }
        return null;
    }
}
