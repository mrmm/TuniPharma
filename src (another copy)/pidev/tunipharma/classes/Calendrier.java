/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.classes;

/**
 *
 * @author elron
 */
public class Calendrier {

    int id_cal;
    int id_pha;
    int heure;
    int minute;
    int jour;
    int mois;
    int annee;

    public Calendrier(int id_cal, int id_pha, int heure, int minute, int jour, int mois, int annee) {
        this.id_cal = id_cal;
        this.id_pha = id_pha;
        this.heure = heure;
        this.minute = minute;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">                          
    // Teste sur le boutton de la formulaire d'acceuil
    public int getId_cal() {
        return id_cal;
    }

    public int getId_pha() {
        return id_pha;
    }

    public int getHeure() {
        return heure;
    }

    public int getMinute() {
        return minute;
    }

    public int getJour() {
        return jour;
    }

    public int getMois() {
        return mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setId_cal(int id_cal) {
        this.id_cal = id_cal;
    }

    public void setId_pha(int id_pha) {
        this.id_pha = id_pha;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
    // </editor-fold>
    
    
}
