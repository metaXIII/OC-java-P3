package com.metaxiii.game;

import com.metaxiii.enumeration.ListMode;
import com.metaxiii.file.App;

import java.util.InputMismatchException;

public class PlusOuMoins extends Game {
    private int proposal;
    private int error;
    private String gameMode;

    PlusOuMoins(ListMode gameMode, boolean isdev) {
        App app = new App();
        this.errorMax = app.getError();
        this.error = 0;
        this.proposal = 0;
        this.gameMode = gameMode.name();
        this.isDev = isdev;
    }

    public void init() {
        System.out.println("Jeu du Plus ou moins");
        super.setSolution(this.gameMode);
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
        logger.info("Nombre d'erreur max : " + this.errorMax);
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
            if (this.userTwo.isPlayer() && this.proposal != this.solution) {
                System.out.println("C'est l'ordinateur qui joue");
                this.proposal = (int) (Math.random() * (this.size * 10));
                System.out.println("L'ordi a décidé : " + this.proposal);
                Thread.sleep(2000);
                operate(this.proposal, 2);
            }
        }
    }

    private void operate(int number, int player) {
        int i, a, b;
        i = this.size;
        if (number != this.solution) {
            while (i >= 1) {
                a = (number / i) % 10;
                b = (this.solution / i) % 10;
                if (a == b)
                    System.out.print((a) % 10);
                else if (a > b)
                    System.out.print("-");
                else
                    System.out.print("+");
                i /= 10;
            }
            logger.info("Erreur : "+ (this.error + 1));
            this.error++;
            if (player == 1)
                System.out.println(" Il vous reste " + (this.errorMax - this.error) + " possibilités");
            else
                System.out.println(" L'ordinateur se trompe aussi, il reste " + (this.errorMax - this.error) + " possibilités");
        } else {
            System.out.println("Bravo !!! Vous avez trouvé la solution");
        }
    }
}
