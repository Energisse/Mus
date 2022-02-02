package com.montaury.mus.jeu;

import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MancheTest {

  @BeforeEach
  void setUp() {
    manche = new Manche(mock(Evenements.class));
  }

  @Test
  void devrait_terminer_la_manche_si_hordago_au_grand() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Hordago());
    var joueurZaku = unJoueurFaisantChoix(new Kanta());

    var joueurEquipeEsku = new ArrayList<Joueur>();
    joueurEquipeEsku.add(joueurEsku);
    var joueurEquipeZaku = new ArrayList<Joueur>();
    joueurEquipeZaku.add(joueurZaku);


    var equipeEsku = new Equipe("EquipeEsku",joueurEquipeEsku);
    var equipeZaku = new Equipe("EquipeZaku",joueurEquipeZaku);

    var resultat = manche.jouer(new Opposants(equipeEsku, equipeZaku));

    assertThat(resultat.vainqueur()).isNotNull();
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_terminer_la_manche_si_un_joueur_a_40_points() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Imido(), new Gehiago(2));
    var joueurZaku = unJoueurFaisantChoix(new Gehiago(40), new Tira());

    var joueurEquipeEsku = new ArrayList<Joueur>();
    joueurEquipeEsku.add(joueurEsku);
    var joueurEquipeZaku = new ArrayList<Joueur>();
    joueurEquipeZaku.add(joueurZaku);

    var equipeEsku = new Equipe("EquipeEsku",joueurEquipeEsku);
    var equipeZaku = new Equipe("EquipeZaku",joueurEquipeZaku);

    var resultat = manche.jouer(new Opposants(equipeEsku, equipeZaku));


    assertThat(resultat.vainqueur()).isEqualTo(equipeEsku);
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_changer_l_ordre_des_opposants_a_la_fin_du_tour() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Hordago());
    var joueurZaku = unJoueurFaisantChoix(new Kanta());

    var joueurEquipeEsku = new ArrayList<Joueur>();
    joueurEquipeEsku.add(joueurEsku);
    var joueurEquipeZaku = new ArrayList<Joueur>();
    joueurEquipeZaku.add(joueurZaku);


    var equipeEsku = new Equipe("EquipeEsku",joueurEquipeEsku);
    var equipeZaku = new Equipe("EquipeZaku",joueurEquipeZaku);

    var opposants = new Opposants(equipeEsku, equipeZaku);
    var resultat = manche.jouer(opposants);


    assertThat(opposants.dansLOrdre()).containsExactly(joueurZaku, joueurEsku);
  }

  private Manche manche;
}
