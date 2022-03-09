package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;

import javax.print.event.PrintJobEvent;
import java.util.ArrayList;
import java.util.List;

public class Participants {
  private final List<Joueur> dansLOrdre;

  public Participants(List<Joueur> dansLOrdre) {
    this.dansLOrdre = dansLOrdre;
  }

  public boolean aucun() {
    return dansLOrdre.isEmpty();
  }

  public boolean estUnique() {
    return dansLOrdre.size() == 1;
  }

  public Joueur premier() {
    return dansLOrdre.get(0);
  }

  public List<Joueur> adversairesDe(Joueur joueurParlant) {
    List<Joueur> joueursDevantParler = new ArrayList<Joueur>();
    for( Joueur joueur: dansLOrdre()){
      if(joueur.getEquipe() != joueurParlant.getEquipe()){
        joueursDevantParler.add((joueur));
      }
    }

    return joueursDevantParler;
  }

  public Iterable<Joueur> dansLOrdre() {
    return dansLOrdre;
  }

  public Participants retirer(Joueur joueur) {
    var joueurs = new ArrayList<>(dansLOrdre);
    joueurs.remove(joueur);
    return new Participants(joueurs);
  }
}
