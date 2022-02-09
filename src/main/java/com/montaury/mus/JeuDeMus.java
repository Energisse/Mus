package com.montaury.mus;

import com.montaury.mus.jeu.Partie;
import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.Opposants;
import java.util.Scanner;

public class JeuDeMus {
  public static void main(String[] args) {
    System.out.print("Entrez votre nom: ");
    var nomJoueur = new Scanner(System.in).next();
    var joueurHumain = Joueur.humain(nomJoueur);

    var equipeHumain = new Equipe(joueurHumain, Joueur.ordinateur("Co-equipier Bruno"));
    var equipeOrdinateurs = new Equipe(Joueur.ordinateur("Opposant Pedro"), Joueur.ordinateur("Opposant Alex"));

    var partie = new Partie(new AffichageEvenements(joueurHumain));
    var resultat = partie.jouer(new Opposants(equipeHumain, equipeOrdinateurs));

    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().nom());
  }
}
