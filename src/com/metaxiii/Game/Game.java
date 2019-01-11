package com.metaxiii.Game;

import java.util.Scanner;

public class Game {
    protected int size;
    protected int solution;
    Scanner sc;
    String choice;
    String mode;

    public Game() {
        this.sc = new Scanner(System.in);
        this.choice = "true";
        this.mode = "";
    }

    public void initGame() {
        while (!this.choice.equals("O") && !this.choice.equals("")) {
            gameChoice();
            System.out.println("Souhaitez vous quitter l'application ?");
            System.out.println("O - oui");
            System.out.println("N - non");
            this.choice = this.sc.nextLine().toUpperCase();
        }
    }

    private void gameChoice() {
        System.out.println("A quel jeu souhaitez vous jouer ?");
        System.out.println("1 - Plus ou moins");
        System.out.println("2 - Mastermind");
        while (!this.choice.equals("1") && !this.choice.equals("2")) {
            this.choice = this.sc.nextLine();
            if (this.choice.equals("1") || this.choice.equals("2")) {
                System.out.println("Quel mode de jeu ? ");
                System.out.println("1 - Challenger");
                System.out.println("2 - Defenseur");
                System.out.println("3 - Duel");
                this.mode = this.sc.nextLine();
                if (this.mode.equals("1") || this.mode.equals("2") || this.mode.equals("3")) {
                    if (this.choice.equals("1")) {
                        PlusOuMoins plusOuMoins = new PlusOuMoins(this.mode);
                        plusOuMoins.init();
                    } else {
                        Mastermind mastermind = new Mastermind(this.mode);
                    }
                } else
                    System.out.println("Mauvaise entrée ! Veuillez répondre 1, 2 ou 3");
            } else
                System.out.println("Mauvaise entrée ! Veuillez répondre 1 ou 2");
        }
    }

    protected void showSize() {
        int i = this.size;
        while (i >= 1) {
            System.out.print("-");
            i /= 10;
        }
        System.out.println();
    }

    protected void getSize() {
        this.size = 1;
        while (this.solution / this.size > 1) {
            this.size *= 10;
        }
    }
}
