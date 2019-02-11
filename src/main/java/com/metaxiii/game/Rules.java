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
    //Propriétés
    protected boolean isDev = false;
    protected final Logger logger = LogManager.getLogger(Game.class);
    protected int size;
    protected int proposal;
    protected int error;
    protected int errorMax;
    protected int solution;
    Scanner sc;
    ListGame gameChoice;
    ListMode gameMode;
    User userOne;
    User userTwo;
    protected String game;
    protected String computer;
    protected String toSave;

    /**
     * Constructeur
     */
    Rules() {
        App app = new App();
        this.solution = 0;
        this.errorMax = app.getError();
        this.sc = new Scanner(System.in);
    }

    /**
     * Imprime la taille de la solution
     */
    void showSize() {
        int i = this.size;
        System.out.print("Combinaison secrète : ");
        while (i >= 1) {
            System.out.print("-");
            i /= 10;
        }
        System.out.println();
    }

    /**
     * Modifie la taille de la solution
     */
    void getSize() {
        this.size = 1;
        while (this.solution / this.size >= 10) {
            this.size *= 10;
        }
        if (this.isDev)
            System.out.println("Combinaison secrète : " + this.solution);
    }

    /**
     * Paramétrage de la solution en fonction du mode de jeu
     *
     * @param gameMode {String} mode de jeu
     */
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

    /**
     * Paramétrage de la solution quand le joueur 1 est défenseur
     */
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

    /**
     * Modifie la solution en fonction du mode de jeu pour le Mastermind
     *
     * @param gameMode {String} mode de jeu
     */
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


    /**
     * Lecture du fichier config.properties
     *
     * @return solution
     */
    public int setSolutionDefault() {
        App app = new App();
        int limit;
        limit = (int) Math.pow(10, app.getSize());
        int solution;
        solution = (int) (Math.random() * limit) + 1;
        return solution;
    }

    /**
     * Génère une solution pour le mastermind en fonction des paramètres pour les possibles values
     *
     * @return solution
     */
    public int getSolutionDefault() {
        App app;
        int size;
        int i;
        String el;
        int limit;
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

    /**
     * Lancement du jeu
     * Affichage de la solution si dev dans le fichier de logs
     */
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

    /**
     * Règle principale du jeu
     *
     * @throws InterruptedException pas nécessaire. Ajouté par soucis de "beauté du code"
     */
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
                if (this.game.equals("plus ou moins"))
                    this.proposal = this.solvePlusOuMoins();
                else
                    this.proposal = this.solveMastermind();
                System.out.println("L'ordi a décidé : " + this.proposal);
                Thread.sleep(2000);
                operate(this.proposal, 2);
            }
        }
    }

    /**
     * @return int solution
     */
    protected abstract int solveMastermind();

    /**
     * @return int solution
     */
    protected abstract int solvePlusOuMoins();

    /**
     * Méthode réécrite dans les enfants
     *
     * @param proposal {int} proposition rentrée par l'utilisateur
     * @param player   {int} joueur
     */
    protected abstract void operate(int proposal, int player);

}
