package com.metaxiii;

import com.metaxiii.file.App;
import com.metaxiii.game.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        boolean isDev;
        App app = new App();
        Game game = null;
        Logger logger = LogManager.getLogger(Main.class);
        logger.info("Début du programme");
        isDev = app.checkDev();


        for (String arg : args) {
            if (arg.contains("dev"))
                isDev = true;
        }
        if (isDev) {
            logger.debug("L'utilisateur est dev");
            game = new Game(true);
        } else
            game = new Game();
        game.initGame();
    }
}
