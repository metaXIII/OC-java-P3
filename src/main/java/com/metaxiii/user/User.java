package com.metaxiii.user;

public class User {
    private boolean isPlayer;

    public User(boolean user) {
        this.isPlayer = user;
    }

    public boolean isPlayer() {
        return isPlayer;
    }
}
