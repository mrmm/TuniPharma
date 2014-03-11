/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.classes;

import java.sql.Date;

/**
 *
 * @author Chiheb
 */
public class Statistiques {

    int id_stat;
    int type_stat;
    int valeur_stat;
    int mois;
    int id_pha;
    int annee;

    public final static int STAT_RECHERCHE = 1;
    public final static int STAT_INSCRIPTION = 2;
    public final static int STAT_VOTE = 3;

    public Statistiques(int id_stat, int type_stat, int valeur_stat, int mois, int id_pha, int annee) {
        this.id_stat = id_stat;
        this.type_stat = type_stat;
        this.valeur_stat = valeur_stat;
        this.mois = mois;
        this.id_pha = id_pha;
        this.annee = annee;
    }

    public int getId_stat() {
        return id_stat;
    }

    public int getType_stat() {
        return type_stat;
    }

    public int getValeur_stat() {
        return valeur_stat;
    }

    public int getMois() {
        return mois;
    }

    public int getId_pha() {
        return id_pha;
    }

    public int getAnnee() {
        return annee;
    }

    public void setId_stat(int id_stat) {
        this.id_stat = id_stat;
    }

    public void setType_stat(int type_stat) {
        this.type_stat = type_stat;
    }

    public void setValeur_stat(int valeur_stat) {
        this.valeur_stat = valeur_stat;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public void setId_pha(int id_pha) {
        this.id_pha = id_pha;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
