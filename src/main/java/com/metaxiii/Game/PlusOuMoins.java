package com.metaxiii.Game;

import com.metaxiii.Enum.ListMode;

import java.util.InputMismatchException;

public class PlusOuMoins extends Game {
    private int proposal;
    private int error;
    private int errorMax;
    private String gameMode;

    PlusOuMoins(ListMode gameMode) {
        this.errorMax = 4;
        this.error = 0;
        this.proposal = 0;
        this.gameMode = gameMode.name();
    }

    public void init() {
        System.out.println("Jeu du plus ou moins");
        super.setSolution(gameMode);
        super.getSize();
        super.showSize();
        try {
            game();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fin du jeu");
    }

    private void game() throws InterruptedException {
        while (this.proposal != this.solution && this.error != this.errorMax) {
            if (this.userOne.isPlayer()) {
                System.out.print("Proposition : ");
                try {
                    this.proposal = sc.nextInt();
                } catch (InputMismatchException exception) {
                    sc.next();
                    System.out.println("Vous n'avez pas rentré de chiffre - Vous perdez un point de pénalité");
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
