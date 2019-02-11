package com.metaxiii.game;

import com.metaxiii.enumeration.ListMode;
import com.metaxiii.file.App;

public class PlusOuMoins extends Game {
    private String gameMode;

    /**
     * Constructeur
     *
     * @param gameMode {Listmode} mode de jeu
     * @param isdev    {boolean} developpeur
     */
    PlusOuMoins(ListMode gameMode, boolean isdev, String game) {
        App app = new App();
        this.errorMax = app.getError();
        this.error = 0;
        this.proposal = 0;
        this.gameMode = gameMode.name();
        this.isDev = isdev;
        this.game = game;
    }

    /**
     * Lancement du jeu
     */
    public void init() {
        System.out.println("Jeu du Plus ou moins");
        super.setSolution(this.gameMode);
        super.init();
    }

    /**
     * Méthode réécrite en utilisant le polymorphisme
     *
     * @param number {int} proposition faite par le joueur
     * @param player {int} joueur
     */
    @Override
    protected void operate(int number, int player) {
        int i, a, b, temp;
        i = this.size;
        if (number != this.solution) {
            this.computer = "";
            while (i >= 1) {
                a = (number / i) % 10;
                b = (this.solution / i) % 10;
                if (a == b) {
                    System.out.print((a) % 10);
                    this.computer += Integer.toString((a) % 10);
                } else if (a > b) {
                    System.out.print("-");
                    this.computer += "-";
                } else {
                    System.out.print("+");
                    this.computer += "+";
                }
                i /= 10;
            }
            logger.info("Erreur : " + (this.error + 1));
            this.error++;
            temp = this.errorMax - this.error;
            if (player == 1)
                System.out.println(" Il vous reste " + temp + " possibilités");
            else
                System.out.println(" L'ordinateur se trompe aussi, il reste " + temp + " possibilités");
        } else {
            System.out.println("Bravo !!! Vous avez trouvé la solution");
        }
    }

    public int solvePlusOuMoins() {
        String el = "";
        int lenght;
        int ordiProposal;
        lenght = Integer.toString(this.solution).length();
        if (this.proposal == 0) {
            //Proposition départ
            for (int i = 0; i < lenght; i++) {
                el += "5";
            }
        } else {
            System.out.println("Ancienne proposition " + this.proposal);
            System.out.println(this.computer);
            for (int i = 0; i < lenght; i++) {
                char data;
                int conversion;
                switch (this.computer.charAt(i)) {
                    case '+':
                        data = Integer.toString(this.proposal).charAt(i);
                        conversion = Character.getNumericValue(data) + (Character.getNumericValue(data) / 2);
                        if (conversion >= 10)
                            conversion = 9;
                        el += conversion;
                        break;
                    case '-':
                        data = Integer.toString(this.proposal).charAt(i);
                        conversion = Character.getNumericValue(data) - (Character.getNumericValue(data) / 2);
                        if (Character.getNumericValue(data) / 2 == 0)
                            conversion = 0;
                        el += conversion;
                        break;
                    default:
                        data = Integer.toString(this.proposal).charAt(i);
                        el += data;
                        break;
                }
            }
        }

        ordiProposal = Integer.parseInt(el);
        return ordiProposal;
    }
}
