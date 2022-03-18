package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Opposants {

  private Joueur joueurEsku;
  private Joueur joueurZaku;
  private List<Equipe> listeEquipe;
  private LinkedList<Joueur> listeJoueur;

  public Opposants(Joueur joueurEsku, Joueur joueurZaku) {
     this(new Equipe("Equipe1",List.of(joueurEsku)),new Equipe("Equipe2",List.of(joueurZaku)));
  }
  public Opposants(Equipe equipeEsku, Equipe equipeZaku) {
    this.listeEquipe = new ArrayList<>();
    this.listeJoueur = new LinkedList<>();

    this.listeEquipe.add(equipeEsku);
    this.listeEquipe.add(equipeZaku);

    this.joueurEsku = equipeEsku.getListejoueurs().get(0);
    this.joueurZaku = equipeZaku.getListejoueurs().get(equipeZaku.getListejoueurs().size()-1);

    for (int indice = 0; indice < equipeEsku.getListejoueurs().size(); indice++) {
      this.listeJoueur.add(equipeEsku.getListejoueurs().get(indice));
      this.listeJoueur.add(equipeZaku.getListejoueurs().get(indice));
    }
  }

  public void tourner() {
    Joueur tmp = this.listeJoueur.removeFirst();
    this.listeJoueur.addLast(tmp);

    this.joueurEsku = this.listeJoueur.getFirst();
    this.joueurZaku = this.listeJoueur.getLast();

    var tmpEquipe = this.listeEquipe.get(0);
    this.listeEquipe.set(0,this.listeEquipe.get(1));
    this.listeEquipe.set(1,tmpEquipe);
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

