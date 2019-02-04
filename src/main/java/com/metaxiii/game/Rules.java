package com.metaxiii.game;

import com.metaxiii.enumeration.ListGame;
import com.metaxiii.enumeration.ListMode;
import com.metaxiii.file.App;
import com.metaxiii.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Rules {
    protected boolean isDev = false;
    protected final Logger logger = LogManager.getLogger(Game.class);
    int size;
    protected int proposal;
    protected int error;
    protected int errorMax;
    int solution;
    Scanner sc;
    ListGame gameChoice;
    ListMode gameMode;
    User userOne;
    User userTwo;

    Rules() {
        App app = new App();
        this.solution = 0;
        this.errorMax = app.getError();
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
            DefenseurPlay();
        } else {
            this.userOne = new User(true);
            this.userTwo = new User(true);
            this.solution = this.setSolutionDefault();
        }
    }

    private void DefenseurPlay() {
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
    }

    protected void setSolutionMastermind(String gameMode) {
        if (gameMode.equals("Challenger")) {
            this.userOne = new User(true);
            this.userTwo = new User(false);
            this.solution = this.getSolutionDefault();
        } else if (gameMode.equals("Defenseur")) {
            DefenseurPlay();
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

    protected void init() {
        getSize();
        if (isDev) {
            logger.info("La solution est : " + this.solution);
        }
        showSize();
        try {
            game();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fin du jeu");
    }


    private void game() throws InterruptedException {
        logger.info("Nombre d'erreur max : " + this.errorMax);
        if (this.userOne.isPlayer() && this.userTwo.isPlayer())
            errorMax *= 2;
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

    protected abstract void operate(int proposal, int i);

}
