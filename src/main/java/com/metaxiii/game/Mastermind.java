package com.metaxiii.game;

import com.metaxiii.enumeration.ListMode;
import com.metaxiii.file.App;

import java.util.InputMismatchException;

public class Mastermind extends Game {
    private int proposal;
    private int error;
    private String gameMode;

    Mastermind(ListMode gameMode, boolean isDev) {
        App app = new App();
        this.error = 0;
        this.proposal = 0;
        this.gameMode = gameMode.name();
        this.isDev = isDev;
        this.errorMax = app.getError();
    }

    public void init() {
        System.out.println("Jeu du mastermind");
        super.setSolutionMastermind(this.gameMode);
        super.getSize();
        if (isDev) {
            logger.info("La solution est : " + this.solution);
        }
        super.showSize();
        try {
            game();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fin du jeu");
    }

    private void game() throws InterruptedException {
        while (this.proposal != this.solution && this.error < this.errorMax) {
            if (this.userOne.isPlayer()) {
                System.out.print("Proposition : ");
                try {
                    this.proposal = sc.nextInt();
                } catch (InputMismatchException exception) {
                    sc.next();
                    System.out.println("Vous n'avez pas rentré de chiffre - Vous perdez un point de pénalité");
                    logger.error("L'utilisateur n'a pas saisi de nombre");
                }
                operate(this.proposal, 1);
            }
            if (this.userTwo.isPlayer()) {
                System.out.println("C'est l'ordinateur qui joue");
                this.proposal = (int) (Math.random() * (this.size * 10));
                System.out.println("L'ordi a décidé : " + this.proposal);
                Thread.sleep(3000);
                operate(this.proposal, 2);
            }
        }
    }

    private void operate(int number, int player) {
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
