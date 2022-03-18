package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.List;

public class Equipe {

    private final String nom;

    private List<Joueur> listejoueurs;

    public Equipe(String nom,List<Joueur> joueurs){
        this.listejoueurs = joueurs;
        for(int indice = this.listejoueurs.size()-1; indice >= 0; indice--){
            this.listejoueurs.get(indice).setEquipe(this);
        }
        this.nom = nom;
    }

    public List<Joueur> getListejoueurs() {
        return listejoueurs;
    }

    public String nom(){
        return nom;
    }
}
