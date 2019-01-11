package com.metaxiii.Game;

public class Mastermind extends Game {

    private int proposal;
    private int error;
    private int errorMax;


    /**
     * Constructeur
     */
    public Mastermind(String mode) {
        this.solution = 1234;
        this.errorMax = 4;
        this.error = 0;
        this.proposal = 0;
        this.mode = mode;
        System.out.println("Jeu du Mastermind");
    }


    public void game() {
    }


}
