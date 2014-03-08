/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.classes;

import java.util.Date;

/**
 *
 * @author elron
 */
public class Evenement {

    int id_event;
    int id_pha;
    Date date_event;
    String nom_event;
    String desc_event;
    Boolean etat_event;

    public Evenement(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
        this.id_event = id_event;
        this.id_pha = id_pha;
        this.date_event = date_event;
        this.nom_event = nom_event;
        this.desc_event = desc_event;
        this.etat_event = etat_event;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">                          
    // Teste sur le boutton de la formulaire d'acceuil
    public void setId_pha(int id_pha) {
        this.id_pha = id_pha;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public void setDesc_event(String desc_event) {
        this.desc_event = desc_event;
    }

    public void setEtat_event(Boolean etat_event) {
        this.etat_event = etat_event;
    }

    public int getId_event() {
        return id_event;
    }

    public int getId_pha() {
        return id_pha;
    }

    public Date getDate_event() {
        return date_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public String getDesc_event() {
        return desc_event;
    }

    public Boolean isEtat_event() {
        return etat_event;
    }
    // </editor-fold>
    
}
