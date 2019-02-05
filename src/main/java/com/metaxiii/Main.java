package com.metaxiii;

import com.metaxiii.file.App;
import com.metaxiii.game.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        //Déclaration
        boolean isDev;
        App app = new App();
        Game game = null;
        Logger logger = LogManager.getLogger(Main.class);

        //Lecture des arguments
        for (String arg : args) {
            if (arg.contains("dev"))
                isDev = true;
        }

        //Lecture de dev avec le config.properties
        logger.info("Début du programme");
        isDev = app.checkDev();

        if (isDev) {
            logger.debug("L'utilisateur est dev");
            game = new Game(true);
        } else
            game = new Game();

        //Lancement du jeu
        game.initGame();
    }
}
