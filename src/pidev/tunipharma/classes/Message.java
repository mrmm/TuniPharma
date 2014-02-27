/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.classes;

import java.sql.Date;

/**
 *
 * @author elron
 */
public class Message {

    int id_msg;
    int id_src;
    int id_dst;
    String sujet_msg;
    String corps_msg;
    Date date_msg;
    Boolean etat_msg;

    public Message(int id_msg, int id_src, int id_dst, String sujet_msg, String corps_msg, Date date_msg, Boolean etat_msg) {
        this.id_msg = id_msg;
        this.id_src = id_src;
        this.id_dst = id_dst;
        this.sujet_msg = sujet_msg;
        this.corps_msg = corps_msg;
        this.date_msg = date_msg;
        this.etat_msg = etat_msg;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">                          
    // Teste sur le boutton de la formulaire d'acceuil

    public void setId_msg(int id_msg) {
        this.id_msg = id_msg;
    }

    public void setId_src(int id_src) {
        this.id_src = id_src;
    }

    public void setId_dst(int id_dst) {
        this.id_dst = id_dst;
    }

    public void setSujet_msg(String sujet_msg) {
        this.sujet_msg = sujet_msg;
    }

    public void setCoprs_msg(String coprs_msg) {
        this.corps_msg = coprs_msg;
    }

    public void setDate_msg(Date date_msg) {
        this.date_msg = date_msg;
    }

    public void setEtat_msg(Boolean etat_msg) {
        this.etat_msg = etat_msg;
    }

    public int getId_msg() {
        return id_msg;
    }

    public int getId_src() {
        return id_src;
    }

    public int getId_dst() {
        return id_dst;
    }

    public String getSujet_msg() {
        return sujet_msg;
    }

    public String getCorps_msg() {
        return corps_msg;
    }

    public Date getDate_msg() {
        return date_msg;
    }

    public Boolean isEtat_msg() {
        return etat_msg;
    }
    // </editor-fold>
    
    
}
