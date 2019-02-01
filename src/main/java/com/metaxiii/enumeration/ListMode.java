package com.metaxiii.enumeration;

public enum ListMode {
    Challenger("1"),
    Defenseur("2"),
    Duel("3");

    private String value;

    ListMode(String value) {
        this.value = value;
    }

    public static ListMode getMode(String el) {
        for (ListMode element : ListMode.values()) {
            if (el.equals(element.value))
                return element;
        }
        return null;
    }

}
