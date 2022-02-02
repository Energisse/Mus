package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Opposants {
  private Equipe equipeEsku;
  private Equipe equipeZaku;

  public Opposants(Equipe equipeEsku, Equipe equipeZaku) {
    //verifier la taille des equipe
    this.equipeEsku = equipeEsku;
    this.equipeZaku = equipeZaku;
  }

  public void tourner() {
    var tmp = equipeEsku;
    equipeEsku = equipeZaku;
    equipeZaku = tmp;
    for (int indice = 0; indice > equipeEsku.getListejoeurs().size()-1;indice++){
      var tmpJoueur= equipeEsku.getListejoeurs().get(indice);
      equipeEsku.getListejoeurs().set(indice,equipeEsku.getListejoeurs().get(indice+1));
      equipeEsku.getListejoeurs().set(indice+1,tmpJoueur);
    }
  }

  public Equipe equipeEsku() {
    return equipeEsku;
  }

  public Equipe equipeZaku() {
    return equipeZaku;
  }

  public List<Joueur> dansLOrdre() {
    List<Joueur> listJoueur = new ArrayList<Joueur>();
    for (int indice = 0; indice < equipeEsku.getListejoeurs().size(); indice++) {
      listJoueur.add(equipeEsku.getListejoeurs().get(indice));
      listJoueur.add(equipeZaku.getListejoeurs().get(indice));
    }
    return listJoueur;
  }
}
