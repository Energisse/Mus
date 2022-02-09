package com.montaury.mus.jeu.joueur;

import java.util.List;

public class Equipe {
    private Joueur joueurA;
    private Joueur joueurB;
    private String nom;

    public Equipe (Joueur joueurA, Joueur joueurB, String nom){
        this.joueurA = joueurA;
        this.joueurB = joueurB;
        this.nom = nom;
    }

    public Joueur getJoueurA() {
        return joueurA;
    }

    public Joueur getJoueurB() {
        return joueurB;
    }

    public String getNom() {
        return nom;
    }
}
