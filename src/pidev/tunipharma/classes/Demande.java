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
public class Demande {

    int id_dmd;
    int type_dmd;
    Date date_dmd;
    int id_cpt_dmd;
    int id_concerne_dmd;

    public Demande(int id_dmd, int type_dmd, Date date_dmd, int id_cpt_dmd, int id_concerne_dmd) {
        this.id_dmd = id_dmd;
        this.type_dmd = type_dmd;
        this.date_dmd = date_dmd;
        this.date_dmd = date_dmd;
        this.id_concerne_dmd = id_concerne_dmd;
    }

    public void setId_dmd(int id_dmd) {
        this.id_dmd = id_dmd;
    }

    public void setType_dmd(int type_dmd) {
        this.type_dmd = type_dmd;
    }

    public void setDate_dmd(Date date_dmd) {
        this.date_dmd = date_dmd;
    }

    public void setId_cpt_dmd(int id_cpt_dmd) {
        this.id_cpt_dmd = id_cpt_dmd;
    }

    public void setId_concerne_dmd(int id_concerne_dmd) {
        this.id_concerne_dmd = id_concerne_dmd;
    }

    public int getId_dmd() {
        return id_dmd;
    }

    public int getType_dmd() {
        return type_dmd;
    }

    public Date getDate_dmd() {
        return date_dmd;
    }

    public int getId_cpt_dmd() {
        return id_cpt_dmd;
    }

    public int getId_concerne_dmd() {
        return id_concerne_dmd;
    }
    
    public String getNomTypeDmd(int type){
        String s="";
        switch(type){
            case 1: s="Comptes";
                break;
            case 2:
                s="Evenements";
                break;
        }
        return s;
    }
}
