package com.montaury.mus;

import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.Partie;
import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.Opposants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JeuDeMus {
  public static void main(String[] args) {
    System.out.print("Entrez votre nom: ");
    var nomJoueur = new Scanner(System.in).next();
    var joueurHumain = Joueur.humain(nomJoueur);
    System.out.print("Entrez votre nom d'equipe: ");
    var nomEquipe = new Scanner(System.in).next();

    var partie = new Partie(new AffichageEvenements(joueurHumain));

    List<Joueur> listeJoueurEquipe1 = new ArrayList<Joueur>();
    listeJoueurEquipe1.add(joueurHumain);
    listeJoueurEquipe1.add(Joueur.ordinateur());
    var equipe1 = new Equipe(nomEquipe,listeJoueurEquipe1);

    List<Joueur> listeJoueurEquipe2 = new ArrayList<Joueur>();
    listeJoueurEquipe2.add(Joueur.ordinateur());
    listeJoueurEquipe2.add(Joueur.ordinateur());
    var equipe2 = new Equipe("Equipe Ordinateur",listeJoueurEquipe2);

    var resultat = partie.jouer(new Opposants(equipe1, equipe2));

    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().nom());
  }
}
