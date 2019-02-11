package com.metaxiii.game;

import com.metaxiii.enumeration.ListMode;
import com.metaxiii.file.App;

public class Mastermind extends Game {

    private String gameMode;
    private int limit;
    private App app = new App();

    /**
     * Constructeur
     *
     * @param gameMode {Listmode} mode de jeu
     * @param isDev    {boolean}
     */
    Mastermind(ListMode gameMode, boolean isDev, String game) {
        this.error = 0;
        this.proposal = 0;
        this.gameMode = gameMode.name();
        this.isDev = isDev;
        this.game = game;
        this.toSave = "";
    }

    /**
     * Lancement du jeu
     */
    public void init() {
        System.out.println("Jeu du mastermind");
        super.setSolutionMastermind(this.gameMode);
        super.init();
    }

    /**
     * Méthode réécrite
     *
     * @param number {int} proposition faite par le joueur
     * @param player {int} joueur
     */
    @Override
    protected void operate(int number, int player) {
        String solution = String.valueOf(this.solution);
        String proposition = String.valueOf(number);
        int present = 0;
        int place = 0;
        for (int i = 0; i < proposition.length(); i++) {
            for (int j = i; j < solution.length(); j++) {
                boolean isPresent = proposition.charAt(i) == solution.charAt(j);
                if (isPresent && i == j) {
                    place++;
                    if (this.userTwo.isPlayer()) {
                        char[] element = toSave.toCharArray();
                        element[i] = proposition.charAt(i);
                        this.toSave = String.valueOf(element);
                    }
                } else if (isPresent) {
                    present++;
                }
            }
        }
        System.out.println("present : " + present);
        System.out.println("place : " + place);
        if (!solution.equals(proposition)) {
            if (player == 1)
                System.out.println("Il vous reste " + (this.errorMax - this.error) + " possibilités");
            else
                System.out.println("L'ordinateur se trompe aussi, il reste " + (this.errorMax - this.error) + " possibilités");
            error++;
            logger.info("Erreur : " + this.error);
        } else
            System.out.println("Bravo vous avez trouvé ! ");
    }

    public int solveMastermind() {
        String el = "";
        System.out.println("Ancienne proposition : " + this.proposal);
        int lenght;
        lenght = Integer.toString(this.solution).length();
        if (this.proposal == 0 && this.error == 0) {
            for (int i = 0; i < lenght; i++) {
                el += "0";
                toSave += "!";
            }
            if (Integer.toString(this.solution).contains("0")) {
                for (int i = 0; i < lenght; i++) {
                    if (Integer.toString(this.solution).charAt(i) == '0') {
                        char element[] = toSave.toCharArray();
                        element[i] = '0';
                        this.toSave = String.valueOf(element);
                    }
                }
            }
        } else {
            if (!toSave.contains("!"))
                return Integer.parseInt(toSave);
            for (int i = 0; i <= app.getValue(); i++) {
                el = "";
                if (!toSave.contains(Integer.toString(i))) {
                    if (Integer.toString(this.proposal).contains(Integer.toString(i)))
                        i++;
                    el += i;
                    el += i;
                    el += i;
                    el += i;
                    i = app.getValue() + 1;
                }
            }
        }
        return Integer.parseInt(el);
    }
}
