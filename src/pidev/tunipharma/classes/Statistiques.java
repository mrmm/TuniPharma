/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.classes;

/**
 *
 * @author Chiheb
 */
public class Statistiques {

    int id_stat;
    int type_stat;
    int valeur;
    int id_pha;
    
    public final static int STAT_RECHERCHE = 1;
    public final static int STAT_INSCRIPTION = 2;
    public final static int STAT_VOTE = 3;

    public Statistiques(int id_stat, int type_stat, int valeur, int id_pha) {
        this.id_stat = id_stat;
        this.type_stat = type_stat;
        this.valeur = valeur;
        this.id_pha = id_pha;
    }

    public int getId_stat() {
        return id_stat;
    }

    public int getType_stat() {
        return type_stat;
    }

    public int getValeur() {
        return valeur;
    }

    public int getId_pha() {
        return id_pha;
    }

    public static int getSTAT_RECHERCHE() {
        return STAT_RECHERCHE;
    }

    public static int getSTAT_INSCRIPTION() {
        return STAT_INSCRIPTION;
    }

    public static int getSTAT_VOTE() {
        return STAT_VOTE;
    }

    public void setId_stat(int id_stat) {
        this.id_stat = id_stat;
    }

    public void setType_stat(int type_stat) {
        this.type_stat = type_stat;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void setId_pha(int id_pha) {
        this.id_pha = id_pha;
    }
    
    
    
}
