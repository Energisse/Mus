package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.joueur.FausseInterfaceJoueur;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.montaury.mus.jeu.carte.Fixtures.paquetEntierCroissant;
import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class GrandEquipeTest {

    @BeforeEach
    void setUp() {
        interfaceJoueur1 = mock(FausseInterfaceJoueur.class);
        interfaceJoueur2 = mock(FausseInterfaceJoueur.class);
        interfaceJoueur3 = mock(FausseInterfaceJoueur.class);
        interfaceJoueur4 = mock(FausseInterfaceJoueur.class);
        joueur1 = new Joueur("J1", interfaceJoueur1);
        joueur1.donnerCartes(List.of(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE));
        joueur2 = new Joueur("J2", interfaceJoueur2);
        joueur2.donnerCartes(List.of(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.SEPT_COUPE));
        joueur3 = new Joueur("J3", interfaceJoueur3);
        joueur3.donnerCartes(List.of(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE));
        joueur4 = new Joueur("J4", interfaceJoueur4);
        joueur4.donnerCartes(List.of(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE));
        equipe1 = new Equipe("Equipe1",List.of(joueur1,joueur3));
        equipe2 = new Equipe("Equipe2",List.of(joueur2,joueur4));
        opposants = new Opposants(equipe1, equipe2);
    }

    @Test
    void joueur3et4_devrait_pas_jouer_apres_que_hordago_et_kanta_et_lequipe2_devrait_gagner() {
        when(interfaceJoueur1.faireChoixParmi(anyList())).thenReturn(new Hordago());
        when(interfaceJoueur2.faireChoixParmi(anyList())).thenReturn(new Kanta());
        when(interfaceJoueur3.faireChoixParmi(anyList())).thenReturn(new Paso());
        when(interfaceJoueur4.faireChoixParmi(anyList())).thenReturn(new Paso());

        Phase.Resultat resultat = grand.jouer(new AffichageEvenements(joueur1),opposants);

        verify(interfaceJoueur1, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur2, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur3, times(0)).faireChoixParmi(anyList());
        verify(interfaceJoueur4, times(0)).faireChoixParmi(anyList());

        assertThat(resultat.vainqueur().get()).isEqualTo(joueur2.getEquipe());
    }

    @Test
    void joueur3_devrait_pas_jouer_apres_que_hordago_et_tira_lequipe1_devrait_gaganer() {
        when(interfaceJoueur1.faireChoixParmi(anyList())).thenReturn(new Hordago());
        when(interfaceJoueur2.faireChoixParmi(anyList())).thenReturn(new Tira());
        when(interfaceJoueur3.faireChoixParmi(anyList())).thenReturn(new Paso());
        when(interfaceJoueur4.faireChoixParmi(anyList())).thenReturn(new Tira());

        Phase.Resultat resultat = grand.jouer(new AffichageEvenements(joueur1),opposants);

        verify(interfaceJoueur1, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur2, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur3, times(0)).faireChoixParmi(anyList());
        verify(interfaceJoueur4, times(1)).faireChoixParmi(anyList());

        assertThat(resultat.vainqueur().get()).isEqualTo(joueur1.getEquipe());
    }

    @Test
    void joueur4_devrait_pas_jouer_et_lequipe2_devrait_gagner_apres_que_l_equipe1_tira() {
        when(interfaceJoueur1.faireChoixParmi(anyList())).thenReturn(new Imido(),new Tira());
        when(interfaceJoueur2.faireChoixParmi(anyList())).thenReturn(new Gehiago());
        when(interfaceJoueur3.faireChoixParmi(anyList())).thenReturn(new Tira());
        when(interfaceJoueur4.faireChoixParmi(anyList())).thenReturn(new Gehiago());
        Phase.Resultat resultat = grand.jouer(new AffichageEvenements(joueur1),opposants);

        verify(interfaceJoueur1, times(2)).faireChoixParmi(anyList());
        verify(interfaceJoueur2, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur3, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur4, times(0)).faireChoixParmi(anyList());

        assertThat(resultat.vainqueur().get()).isEqualTo(joueur2.getEquipe());
    }

    @Test
    void test() {
        when(interfaceJoueur1.faireChoixParmi(anyList())).thenReturn(new Paso(),new Tira());
        when(interfaceJoueur2.faireChoixParmi(anyList())).thenReturn(new Imido());
        when(interfaceJoueur3.faireChoixParmi(anyList())).thenReturn(new Tira());
        when(interfaceJoueur4.faireChoixParmi(anyList())).thenReturn(new Gehiago());
        Phase.Resultat resultat = grand.jouer(new AffichageEvenements(joueur1),opposants);

        verify(interfaceJoueur1, times(2)).faireChoixParmi(anyList());
        verify(interfaceJoueur2, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur3, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur4, times(0)).faireChoixParmi(anyList());

        assertThat(resultat.vainqueur().get()).isEqualTo(joueur2.getEquipe());
    }

    private final Grand grand = new Grand();
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
}
