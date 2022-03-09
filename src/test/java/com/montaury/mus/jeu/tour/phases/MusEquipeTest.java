package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.joueur.FausseInterfaceJoueur;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.montaury.mus.jeu.carte.Fixtures.paquetEntierCroissant;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MusEquipeTest {

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
        equipe1 = new Equipe("Equipe1",List.of(joueur1,joueur3));
        equipe2 = new Equipe("Equipe2",List.of(joueur2,joueur4));
        opposants = new Opposants(equipe1, equipe2);
    }

    @Test
    void joueur3_devrait_pas_jouer_apres_mintza() {
        when(interfaceJoueur1.faireChoixParmi(anyList())).thenReturn(new Mintza());
        when(interfaceJoueur3.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());

        when(interfaceJoueur2.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueur4.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());

        mus.jouer(opposants);

        verify(interfaceJoueur1, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur2, times(0)).faireChoixParmi(anyList());
        verify(interfaceJoueur3, times(0)).faireChoixParmi(anyList());
        verify(interfaceJoueur4, times(0)).faireChoixParmi(anyList());
    }

    @Test
    void joueur4_devrait_pas_jouer_apres_mintza() {
        when(interfaceJoueur1.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueur3.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());

        when(interfaceJoueur2.faireChoixParmi(anyList())).thenReturn(new Mintza());
        when(interfaceJoueur4.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());

        mus.jouer(opposants);

        verify(interfaceJoueur1, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur2, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur3, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur4, times(0)).faireChoixParmi(anyList());
    }

    @Test
    void joueur4et2_devrait_pas_jouer_apres_mintza() {
        when(interfaceJoueur1.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueur3.faireChoixParmi(anyList())).thenReturn(new Mintza());

        when(interfaceJoueur2.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueur4.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());

        mus.jouer(opposants);

        verify(interfaceJoueur1, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur2, times(0)).faireChoixParmi(anyList());
        verify(interfaceJoueur3, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur4, times(0)).faireChoixParmi(anyList());
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
}
