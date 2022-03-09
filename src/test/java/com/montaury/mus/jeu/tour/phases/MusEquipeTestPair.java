package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.joueur.FausseInterfaceJoueur;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class MusEquipeTestPair {

    @BeforeEach
    void setUp() {
        interfaceJoueur1 = mock(FausseInterfaceJoueur.class);
        interfaceJoueur2 = mock(FausseInterfaceJoueur.class);
        interfaceJoueur3 = mock(FausseInterfaceJoueur.class);
        interfaceJoueur4 = mock(FausseInterfaceJoueur.class);
        joueur1 = new Joueur("J1", interfaceJoueur1);
        joueur2 = new Joueur("J2", interfaceJoueur2);
        joueur3 = new Joueur("J3", interfaceJoueur3);
        joueur4 = new Joueur("J4", interfaceJoueur4);
    }

    @Test
    void joueur1et2_devrait_pas_jouer_sans_paires_et_lequipe1_devrait_gagner() {
        when(interfaceJoueur1.faireChoixParmi(anyList())).thenReturn(new Paso());
        when(interfaceJoueur2.faireChoixParmi(anyList())).thenReturn(new Paso());
        when(interfaceJoueur3.faireChoixParmi(anyList())).thenReturn(new Paso());
        when(interfaceJoueur4.faireChoixParmi(anyList())).thenReturn(new Paso());
        joueur1.donnerCartes(List.of(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE));
        joueur2.donnerCartes(List.of(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE));
        joueur3.donnerCartes(List.of(Carte.AS_BATON, Carte.SEPT_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE));
        joueur4.donnerCartes(List.of(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.AS_PIECE, Carte.SIX_COUPE));
        equipe1 = new Equipe("Equipe1",List.of(joueur1,joueur3));
        equipe2 = new Equipe("Equipe2",List.of(joueur2,joueur4));
        opposants = new Opposants(equipe1, equipe2);

        Phase.Resultat resultat = paires.jouer(new AffichageEvenements(joueur1),opposants);

        verify(interfaceJoueur1, times(0)).faireChoixParmi(anyList());
        verify(interfaceJoueur2, times(0)).faireChoixParmi(anyList());
        verify(interfaceJoueur3, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur4, times(1)).faireChoixParmi(anyList());

        assertThat(resultat.vainqueur().get()).isEqualTo(joueur1.getEquipe());
    }

    @Test
    void joueur3et4_devrait_pas_jouer_si_na_pas_de_paires_et_lequipe2_devrait_gagner() {
        when(interfaceJoueur1.faireChoixParmi(anyList())).thenReturn(new Paso());
        when(interfaceJoueur2.faireChoixParmi(anyList())).thenReturn(new Paso());
        when(interfaceJoueur3.faireChoixParmi(anyList())).thenReturn(new Paso());
        when(interfaceJoueur4.faireChoixParmi(anyList())).thenReturn(new Paso());
        joueur1.donnerCartes(List.of(Carte.AS_BATON, Carte.AS_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE));
        joueur2.donnerCartes(List.of(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.SIX_COUPE, Carte.SIX_COUPE));
        joueur3.donnerCartes(List.of(Carte.AS_BATON, Carte.DEUX_BATON, Carte.SEPT_BATON, Carte.SIX_COUPE));
        joueur4.donnerCartes(List.of(Carte.DEUX_BATON, Carte.QUATRE_COUPE, Carte.AS_PIECE, Carte.SIX_COUPE));
        equipe1 = new Equipe("Equipe1",List.of(joueur1,joueur3));
        equipe2 = new Equipe("Equipe2",List.of(joueur2,joueur4));
        opposants = new Opposants(equipe1, equipe2);

        Phase.Resultat resultat = paires.jouer(new AffichageEvenements(joueur1),opposants);

        verify(interfaceJoueur1, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur2, times(1)).faireChoixParmi(anyList());
        verify(interfaceJoueur3, times(0)).faireChoixParmi(anyList());
        verify(interfaceJoueur4, times(0)).faireChoixParmi(anyList());

        assertThat(resultat.vainqueur().get()).isEqualTo(joueur2.getEquipe());

    }


    private final Paires paires = new Paires();
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
