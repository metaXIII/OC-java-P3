package com.metaxiii.Game;

import com.metaxiii.Enum.ListGame;
import com.metaxiii.Enum.ListMode;

public class Game extends Rules {
    protected int errorMax;

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
