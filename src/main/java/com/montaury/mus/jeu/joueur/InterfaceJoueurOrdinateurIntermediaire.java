package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;


import java.util.ArrayList;
import java.util.List;

public class InterfaceJoueurOrdinateurIntermediaire extends InterfaceJoueurOrdinateur{


    //plus de paramètre










    public Choix faireChoixParmi(List<TypeChoix> choixPossibles) {
        var choixOrdinateur = new ArrayList<>(choixPossibles);
        var laMain = main.cartesDuPlusGrandAuPlusPetit();
        TypeChoix choix = TypeChoix.PASO;
        var compteur=0;

        for (var choixCourant : choixOrdinateur) {
            if (choixCourant == TypeChoix.HORDAGO){
                for(var carte : laMain) {
                    if (carte.valeur() > 11) {
                        compteur++;
                    }
                }

                if(compteur>2){
                    choix = TypeChoix.HORDAGO;
                }
            }
        }
        return choixPreetablis.get(choix);

    }

}
