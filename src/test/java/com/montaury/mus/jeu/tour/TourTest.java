package com.montaury.mus.jeu.tour;

import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.Manche;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.montaury.mus.jeu.carte.Fixtures.paquetAvec;
import static com.montaury.mus.jeu.carte.Fixtures.paquetEntierCroissant;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TourTest {

  @BeforeEach
  void setUp() {
    evenementsDeJeu = mock(Evenements.class);
    tour = new Tour(evenementsDeJeu, paquetEntierCroissant(), new Defausse());
  }

  @Test
  void devrait_donner_tous_les_points_au_joueur_esku_si_le_joueur_zaku_fait_tira() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Imido(), new Imido(), new Imido(), new Imido());
    var joueurZaku = unJoueurFaisantChoix(new Tira());
    var opposants = new Opposants(joueurEsku, joueurZaku);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueurEsku.getEquipe(), 8);
    assertThat(score.scoreParEquipe()).containsEntry(joueurZaku.getEquipe(), 0);
  }

  @Test
  void devrait_repartir_les_points_si_tout_est_paso() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Paso(), new Paso(), new Paso(), new Paso());
    var joueurZaku = unJoueurFaisantChoix(new Paso());
    var opposants = new Opposants(joueurEsku, joueurZaku);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueurEsku.getEquipe(), 1);
    assertThat(score.scoreParEquipe()).containsEntry(joueurZaku.getEquipe(), 5);
  }

  @Test
  void devrait_faire_gagner_le_joueur_zaku_si_hordago_au_grand() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Hordago());
    var joueurZaku = unJoueurFaisantChoix(new Kanta());
    var opposants = new Opposants(joueurEsku, joueurZaku);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).contains(joueurZaku.getEquipe());
    assertThat(score.scoreParEquipe()).containsEntry(joueurEsku.getEquipe(), 0);
    assertThat(score.scoreParEquipe()).containsEntry(joueurZaku.getEquipe(), 40);
  }

  @Test
  void devrait_partager_les_points_si_tout_est_idoki() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Imido(), new Imido(), new Imido(), new Imido());
    var joueurZaku = unJoueurFaisantChoix(new Idoki());
    var opposants = new Opposants(joueurEsku, joueurZaku);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueurEsku.getEquipe(), 2);
    assertThat(score.scoreParEquipe()).containsEntry(joueurZaku.getEquipe(), 10);
  }

  @Test
  void devrait_partager_les_points_si_tout_est_gehiago_puis_idoki() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Imido(), new Idoki(), new Imido(), new Idoki(), new Imido(), new Idoki(), new Imido(), new Idoki());
    var joueurZaku = unJoueurFaisantChoix(new Gehiago(2));
    var opposants = new Opposants(joueurEsku, joueurZaku);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueurEsku.getEquipe(), 4);
    assertThat(score.scoreParEquipe()).containsEntry(joueurZaku.getEquipe(), 16);
  }



  @Test
  void devrait_privilegier_le_joueur_esku_si_les_mains_sont_identiques() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Imido(), new Imido(), new Imido(), new Imido());
    var joueurZaku = unJoueurFaisantChoix(new Idoki());
    var opposants = new Opposants(joueurEsku, joueurZaku);
    var score = new Manche.Score(opposants);

    Tour tour = new Tour(evenementsDeJeu, paquetAvec(Carte.AS_BATON, Carte.DEUX_BATON, Carte.TROIS_BATON, Carte.QUATRE_BATON, Carte.AS_COUPE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE), new Defausse());

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueurEsku.getEquipe(), 7);
    assertThat(score.scoreParEquipe()).containsEntry(joueurZaku.getEquipe(), 0);
  }

  @Test
  void devrait_attribuer_les_bonus_au_joueur_ayant_la_meilleure_main_pour_chaque_phase() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Paso(), new Paso());
    var joueurZaku = unJoueurFaisantChoix(new Paso(), new Paso());
    var opposants = new Opposants(joueurEsku, joueurZaku);
    var score = new Manche.Score(opposants);

    Tour tour = new Tour(evenementsDeJeu, paquetAvec(Carte.ROI_BATON, Carte.ROI_COUPE, Carte.VALET_BATON, Carte.AS_EPEE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE, Carte.CINQ_COUPE), new Defausse());

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueurEsku.getEquipe(), 6);
    assertThat(score.scoreParEquipe()).containsEntry(joueurZaku.getEquipe(), 0);
  }

  @Test
  void devrait_effecuter_la_rotation_entre_tous_les_joueurs() {

    String nomJoueur="Martin";
    var joueurHumain = Joueur.humain(nomJoueur);


    String nomEquipe="Les bests";

    List<Joueur> listeJoueurEquipe1 = new ArrayList<Joueur>();
    listeJoueurEquipe1.add(joueurHumain);
    listeJoueurEquipe1.add(Joueur.ordinateur("Ordinateur3"));
    var equipe1 = new Equipe(nomEquipe,listeJoueurEquipe1);

    List<Joueur> listeJoueurEquipe2 = new ArrayList<Joueur>();
    listeJoueurEquipe2.add(Joueur.ordinateur("Ordinateur1"));
    listeJoueurEquipe2.add(Joueur.ordinateur("Ordinateur2"));
    var equipe2 = new Equipe("Equipe Ordinateur",listeJoueurEquipe2);

    var opposants = new Opposants(equipe1,equipe2);



    var JoueurTest1 = opposants.joueurEsku();
    var JoueurTest2 = opposants.dansLOrdre().get(1);
    var JoueurTest3 = opposants.dansLOrdre().get(2);
    var JoueurTest4 = opposants.joueurZaku();

    opposants.tourner();

    assertThat(JoueurTest1).isEqualTo(opposants.joueurZaku());
    assertThat(JoueurTest2).isEqualTo(opposants.joueurEsku());
    assertThat(JoueurTest3).isEqualTo(opposants.dansLOrdre().get(1));
    assertThat(JoueurTest4).isEqualTo(opposants.dansLOrdre().get(2));

    opposants.tourner();

    assertThat(JoueurTest1).isEqualTo(opposants.dansLOrdre().get(2));
    assertThat(JoueurTest2).isEqualTo(opposants.joueurZaku());
    assertThat(JoueurTest3).isEqualTo(opposants.joueurEsku());
    assertThat(JoueurTest4).isEqualTo(opposants.dansLOrdre().get(1));

    opposants.tourner();

    assertThat(JoueurTest1).isEqualTo(opposants.dansLOrdre().get(1));
    assertThat(JoueurTest2).isEqualTo(opposants.dansLOrdre().get(2));
    assertThat(JoueurTest3).isEqualTo(opposants.joueurZaku());
    assertThat(JoueurTest4).isEqualTo(opposants.joueurEsku());

    opposants.tourner();

    assertThat(JoueurTest1).isEqualTo(opposants.joueurEsku());
    assertThat(JoueurTest2).isEqualTo(opposants.dansLOrdre().get(1));
    assertThat(JoueurTest3).isEqualTo(opposants.dansLOrdre().get(2));
    assertThat(JoueurTest4).isEqualTo(opposants.joueurZaku());
  }

  private Evenements evenementsDeJeu;
  private Tour tour;
}
