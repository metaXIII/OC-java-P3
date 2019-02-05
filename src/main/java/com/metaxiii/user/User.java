package com.metaxiii.user;

public class User {
    private boolean isPlayer;

    //getters and setters
    public User(boolean user) {
        this.isPlayer = user;
    }

    public boolean isPlayer() {
        return isPlayer;
    }
}
