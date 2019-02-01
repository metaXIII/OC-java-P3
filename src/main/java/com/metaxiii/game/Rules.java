package com.metaxiii.game;

import com.metaxiii.enumeration.ListGame;
import com.metaxiii.enumeration.ListMode;
import com.metaxiii.file.App;
import com.metaxiii.user.User;

import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Rules {
    protected boolean isDev = false;
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
        System.out.println();
    }

    void getSize() {
        this.size = 1;
        while (this.solution / this.size > 10) {
            this.size *= 10;
        }
        if (this.isDev)
            System.out.println("Combinaison secrète : " + this.solution);
    }

    protected void setSolution(String gameMode) {
        if (gameMode.equals("Challenger")) {
            this.userOne = new User(true);
            this.userTwo = new User(false);
            this.solution = this.setSolutionDefault();
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
            this.solution = this.setSolutionDefault();
        }
    }

    protected void setSolutionMastermind(String gameMode) {
        if (gameMode.equals("Challenger")) {
            this.userOne = new User(true);
            this.userTwo = new User(false);
            this.solution = this.getSolutionDefault();
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
            this.solution = this.getSolutionDefault();
        }
    }


    public int setSolutionDefault() {
        App app = new App();
        int limit;
        limit = (int) Math.pow(10, app.getSize());
        int solution;
        solution = (int) (Math.random() * limit) + 1;
        return solution;
    }

    public int getSolutionDefault() {
        App app;
        int size;
        int limit;
        int i;
        String el;
        el = "";
        i = 0;
        app = new App();
        size = app.getSize();
        limit = app.getValue();
        while (i < size) {
            this.solution = (int) ((Math.random() * limit) + 1);
            el += Integer.toString(this.solution);
            i++;
        }
        this.solution = Integer.parseInt(el);
        return this.solution;
    }
}
