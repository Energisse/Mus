package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Opposants {

  private Joueur joueurEsku;
  private Joueur joueurZaku;
  private List<Equipe> listeEquipe;
  private LinkedList<Joueur> listeJoueur;

  public Opposants(Equipe equipeEsku, Equipe equipeZaku) {
    this.listeEquipe = new ArrayList<>();
    this.listeJoueur = new LinkedList<>();

    this.listeEquipe.add(equipeEsku);
    this.listeEquipe.add(equipeZaku);

    this.joueurEsku = equipeEsku.getListejoeurs().get(0);
    this.joueurZaku = equipeZaku.getListejoeurs().get(equipeZaku.getListejoeurs().size()-1);

    for (int indice = 0; indice < equipeEsku.getListejoeurs().size(); indice++) {
      this.listeJoueur.add(equipeEsku.getListejoeurs().get(indice));
      this.listeJoueur.add(equipeZaku.getListejoeurs().get(indice));
    }
  }

  public void tourner() {
    Joueur tmp = this.listeJoueur.removeFirst();
    this.listeJoueur.addLast(tmp);

    this.joueurEsku = this.listeJoueur.getFirst();
    this.joueurZaku = this.listeJoueur.getLast();
  }

  public Joueur joueurEsku() {
    return joueurEsku;
  }

  public Joueur joueurZaku() {
    return joueurZaku;
  }

  public List<Joueur> dansLOrdre() {
    return this.listeJoueur;
  }

  public List<Equipe> getListeEquipe() {
    return this.listeEquipe;
  }
}
