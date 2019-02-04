package com.metaxiii.game;

import com.metaxiii.enumeration.ListMode;
import com.metaxiii.file.App;

import java.util.InputMismatchException;

public class PlusOuMoins extends Game {
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
        super.init();
    }

    @Override
    protected void operate(int number, int player) {
        int i, a, b, temp;
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
}
