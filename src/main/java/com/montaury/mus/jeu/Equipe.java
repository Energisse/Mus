package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Equipe {

    private final String nom;

    private List<Joueur> listejoeurs;

    public Equipe(String nom,List<Joueur> joueurs){
        this.listejoeurs = joueurs;
        for(int indice = this.listejoeurs.size()-1; indice >= 0;indice--){
            this.listejoeurs.get(indice).setEquipe(this);
        }
        this.nom = nom;
    }

    public List<Joueur> getListejoeurs() {
        return listejoeurs;
    }

    public String nom(){
        return nom;
    }
}
