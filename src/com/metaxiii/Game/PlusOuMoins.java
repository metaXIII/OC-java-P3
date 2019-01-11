package com.metaxiii.Game;

import java.util.InputMismatchException;

public class PlusOuMoins extends Game {
    private int proposal;
    private int error;
    private int errorMax;

    /**
     * Constructeur
     */
    PlusOuMoins(String mode) {
        this.solution = 1234;
        this.errorMax = 4;
        this.error = 0;
        this.proposal = 0;
        this.mode = mode;
    }

    public void init() {
        System.out.println("Jeu du plus ou moins");
        getSize();
        System.out.println("Combinaison secrète : " + this.solution);
        System.out.print("Combinaison secrète : ");
        showSize();
        game();
        System.out.println("Fin du jeu");
    }

    private void game() {
        while (this.proposal != this.solution && this.error != this.errorMax) {
            System.out.print("Proposition : ");
            try {
                this.proposal = sc.nextInt();
            } catch (InputMismatchException exception) {
                sc.next();
                System.out.println("Vous n'avez pas rentré de chiffre - Vous perdez un point de pénalité");
            }
            operate(this.proposal);
        }
    }

    private void operate(int number) {
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
            System.out.println(" Il vous reste " + (this.errorMax - this.error) + " possibilités");
        } else {
            System.out.println("Bravo !!! Vous avez trouvé la solution");
        }
    }
}
