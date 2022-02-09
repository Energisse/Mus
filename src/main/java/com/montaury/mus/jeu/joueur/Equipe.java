package com.montaury.mus.jeu.joueur;

import java.util.List;

public class Equipe {
    private Joueur joueurA;
    private Joueur joueurB;

    public Equipe (Joueur joueurA, Joueur joueurB){
        this.joueurA = joueurA;
        this.joueurB = joueurB;
    }

    public Joueur getJoueurA() {
        return joueurA;
    }

    public Joueur getJoueurB() {
        return joueurB;
    }
}
