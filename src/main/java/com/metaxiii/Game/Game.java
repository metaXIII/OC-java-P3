package com.metaxiii.Game;

import com.metaxiii.Enum.ListGame;
import com.metaxiii.Enum.ListMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game extends Rules {
    protected int errorMax;
    protected final Logger logger = LogManager.getLogger(Game.class);

    public Game(boolean isDev) {
        super();
        this.isDev = isDev;
    }

    public Game() {
        super();
    }

    public void initGame() {
        char rep;
        while (this.gameChoice == null) {
            gameChoiceSelection();
            logger.warn("Fin du jeu");
            System.out.println("Souhaitez vous quitter l'application ?");
            System.out.println("O - oui");
            System.out.println("N - non");
            rep = sc.nextLine().toLowerCase().charAt(0);
            if (rep == 'n') {
                resetGame();
            } else
                System.out.println("Au revoir !");
        }
    }

    private void resetGame() {
        this.gameChoice = null;
        this.gameMode = null;
    }

    private void gameChoiceSelection() {
        while (this.gameChoice == null) {
            System.out.println("A quel jeu souhaitez vous jouer ? \n" +
                    "1 - Plus ou moins \n" +
                    "2 - Mastermind");
            this.gameChoice = ListGame.getGame(this.sc.nextLine());
            if (this.gameChoice != null) {
                while (this.gameMode == null) {
                    System.out.println("Quel mode de jeu ? ");
                    System.out.println("1 - Challenger");
                    System.out.println("2 - Defenseur");
                    System.out.println("3 - Duel");
                    this.gameMode = ListMode.getMode(this.sc.nextLine());
                }
            }
        }
        logger.info("Jeu choisi : " + this.gameChoice + " | mode choisi : " + this.gameMode);
        logger.info("Nombre d'erreur maximum : " + this.errorMax);
        gameChoice();
    }

    private void gameChoice() {
        if (this.gameChoice.name().equals("PlusOuMoins")) {
            PlusOuMoins plusOuMoins = new PlusOuMoins(this.gameMode, this.isDev);
            plusOuMoins.init();
        } else {
            Mastermind mastermind = new Mastermind(this.gameMode, this.isDev);
            mastermind.init();
        }
    }
}
