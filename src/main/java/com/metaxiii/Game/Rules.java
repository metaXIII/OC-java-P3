package com.metaxiii.Game;

import com.metaxiii.Enum.ListGame;
import com.metaxiii.Enum.ListMode;
import com.metaxiii.User.User;

import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Rules {
    int size;
    int solution;
    Scanner sc;
    ListGame gameChoice;
    ListMode gameMode;
    User userOne;
    User userTwo;

    Rules() {
        this.solution = 0;
        this.sc = new Scanner(System.in);
    }

    void showSize() {
        int i = this.size;
        System.out.print("Combinaison secrète : ");
        while (i >= 1) {
            System.out.print("-");
            i /= 10;
        }
        System.out.println("");
    }

    void getSize() {
        this.size = 1;
        while (this.solution / this.size > 10) {
            this.size *= 10;
        }
        System.out.println("Combinaison secrète : " + this.solution);
    }

    protected void setSolution(String gameMode) {
        System.out.println(gameMode);
        if (gameMode.equals("Challenger")) {
            this.userOne = new User(true);
            this.userTwo = new User(false);
            this.solution = this.setSolution();
        } else if (gameMode.equals("Defenseur")) {
            this.userOne = new User(false);
            this.userTwo = new User(true);
            while (this.solution == 0) {
                try {
                    System.out.println("Rentrez un nombre, l'ordinateur devra essayer de le deviner");
                    this.solution = this.sc.nextInt();
                } catch (InputMismatchException e) {
                    this.sc.next();
                    System.out.println("Il vous faut rentrer un nombre !");
                }
            }
        } else {
            this.userOne = new User(true);
            this.userTwo = new User(true);
            this.solution = this.setSolution();
        }
    }

    public int setSolution() {
        int solution;
        solution = (int) (Math.random() * 10000) + 1;
        return solution;
    }
}
