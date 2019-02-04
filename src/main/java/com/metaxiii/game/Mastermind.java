package com.metaxiii.game;

import com.metaxiii.enumeration.ListMode;
import com.metaxiii.file.App;

import java.util.InputMismatchException;

public class Mastermind extends Game {

    private String gameMode;

    Mastermind(ListMode gameMode, boolean isDev) {
        this.error = 0;
        this.proposal = 0;
        this.gameMode = gameMode.name();
        this.isDev = isDev;
    }

    public void init() {
        System.out.println("Jeu du mastermind");
        super.setSolutionMastermind(this.gameMode);
        super.init();
    }

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
                    present++;
                    place++;
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
}
