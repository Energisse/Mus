package com.montaury.mus.jeu;

import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.joueur.FausseInterfaceJoueur;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.Manche.Score;
import com.montaury.mus.jeu.tour.phases.Mus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.List;

import static com.montaury.mus.jeu.carte.Fixtures.paquetEntierCroissant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ScoreTest {

    @BeforeEach
    void setUp() {

        defausse = new Defausse();
        mus = new Mus(paquetEntierCroissant(), defausse, new AffichageEvenements(joueur1));
        interfaceJoueur1 = mock(FausseInterfaceJoueur.class);
        interfaceJoueur2 = mock(FausseInterfaceJoueur.class);
        interfaceJoueur3 = mock(FausseInterfaceJoueur.class);
        interfaceJoueur4 = mock(FausseInterfaceJoueur.class);
        joueur1 = new Joueur("J1", interfaceJoueur1);
        joueur2 = new Joueur("J2", interfaceJoueur2);
        joueur3 = new Joueur("J3", interfaceJoueur3);
        joueur4 = new Joueur("J4", interfaceJoueur4);
        equipe1 = new Equipe("Equipe1", List.of(joueur1,joueur3));
        equipe2 = new Equipe("Equipe2",List.of(joueur2,joueur4));
        opposants = new Opposants(equipe1, equipe2);

        score = new Score(opposants);
    }

    @Test
    void score_est_attribue_a_equipe() {
        //System.out.println(score.scoreParEquipe().get(equipe1));

        int points = 10;
        int expected = 10;
        score.scorer(equipe1,points);

        //System.out.println(score.scoreParEquipe().get(equipe1));
        assertThat(score.scoreParEquipe().get(equipe1)).isEqualTo(expected);

    }

    @Test
    void score_40_est_vainqueur() {
        int points = 40;
        score.scorer(equipe2,points);
        assertThat(score.vainqueur().get()).isEqualTo(equipe2);
    }

    @Test
    void score_attribue_a_equipe_perdante() {
        int points = 40;
        int pointsVaincu = 10;
        score.scorer(equipe1,pointsVaincu);
        score.scorer(equipe2,points);
        score.scorer(equipe1,10);

        assertThat(score.pointsVaincu().get()).isEqualTo(pointsVaincu);

    }

    private Mus mus;
    private InterfaceJoueur interfaceJoueur1;
    private InterfaceJoueur interfaceJoueur2;
    private InterfaceJoueur interfaceJoueur3;
    private InterfaceJoueur interfaceJoueur4;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueur3;
    private Joueur joueur4;
    private Equipe equipe1;
    private Equipe equipe2;
    private Opposants opposants;
    private Defausse defausse;
    private Score score;

}

