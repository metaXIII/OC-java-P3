package com.metaxiii.Game;

import com.metaxiii.Enum.ListGame;
import com.metaxiii.Enum.ListMode;

public class Game extends Rules {

    public Game() {
        super();
    }

    public void initGame() {
        while (this.gameMode == null) {
            gameChoiceSelection();
            System.out.println("Souhaitez vous quitter l'application ?");
            System.out.println("O - oui");
            System.out.println("N - non");
        }
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
            PlusOuMoins plusOuMoins = new PlusOuMoins(this.gameMode);
            plusOuMoins.init();
        } else {
            Mastermind mastermind = new Mastermind();
        }
    }
}
