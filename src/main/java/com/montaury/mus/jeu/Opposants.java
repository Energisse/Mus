package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Opposants {
  private final LinkedList<Joueur> joueursOrdre = new LinkedList<>();

  public Opposants(Equipe equipeEsku, Equipe equipeZaku) {
    joueursOrdre.add(0,equipeEsku.getJoueurA());  //  1 A ESKU
    joueursOrdre.add(1,equipeZaku.getJoueurA());  //  2 A
    joueursOrdre.add(2,equipeEsku.getJoueurB());  //  1 B
    joueursOrdre.add(3,equipeZaku.getJoueurB());  //  2 B ZAKU
  }

  public void tourner() {
    Joueur zakuToEsku = joueursOrdre.pollLast();    //  ZAKU
    joueursOrdre.addFirst(zakuToEsku);
  }

  public Joueur joueurEsku() {
    return joueursOrdre.getFirst();
  }

  public Joueur joueurEskuCoEquipier() {
    return joueursOrdre.get(2);
  }

  public Joueur joueurZaku() {
    return joueursOrdre.getLast();
  }

  public Joueur joueurZakuCoEquipier() {
    return joueursOrdre.get(1);
  }

  public Equipe equipeEsku() {
    return new Equipe(joueurEsku(),joueurEskuCoEquipier());
  }
  public Equipe equipeZaku(){
    return new Equipe(joueurZaku(),joueurZakuCoEquipier());
  }
  public List<Joueur> dansLOrdre() {

    return new ArrayList<>(joueursOrdre); }
}
