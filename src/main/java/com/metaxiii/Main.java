package com.metaxiii;

import com.metaxiii.File.App;
import com.metaxiii.Game.Game;

public class Main {
    public static void main(String[] args) {
        boolean isDev;
        System.out.println("Bonjour, Bienvenu dans ce Projet");

        App app = new App();
        isDev = app.checkDev();
        for (String arg : args) {
            if (arg.contains("dev")) {
                isDev = true;
            }
        }

        if (isDev) {
            Game game = new Game(true);
            game.initGame();
        } else {
            Game game = new Game();
            game.initGame();
        }
    }
}
