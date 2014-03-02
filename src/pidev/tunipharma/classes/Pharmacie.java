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
public class Pharmacie {

    int id_pha;
    int id_resp;
    String nom_pha;
    String addresse_pha;
    int tel_pha;
    int fax_pha;
    int id_cal;
    String lat_gm_pha;
    String long_gm_pha;
    String email_pha;
    int type_pha;
    int ville_pha;
    int gouv_pha;

    public Pharmacie(int id_pha, int id_resp, String nom_pha, String addresse_pha, int tel_pha, int fax_pha, int id_cal, String lat_gm_pha, String long_gm_pha, String email_pha, int type_pha, int ville_pha, int gouv_pha) {
        this.id_pha = id_pha;
        this.id_resp = id_resp;
        this.nom_pha = nom_pha;
        this.addresse_pha = addresse_pha;
        this.tel_pha = tel_pha;
        this.fax_pha = fax_pha;
        this.id_cal = id_cal;
        this.lat_gm_pha = lat_gm_pha;
        this.long_gm_pha = long_gm_pha;
        this.email_pha = email_pha;
        this.type_pha = type_pha;
        this.ville_pha = ville_pha;
        this.gouv_pha = gouv_pha;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">                          
    public int getId_pha() {
        return id_pha;
    }

    public int getId_resp() {
        return id_resp;
    }

    public String getNom_pha() {
        return nom_pha;
    }

    public String getAddresse_pha() {
        return addresse_pha;
    }

    public int getTel_pha() {
        return tel_pha;
    }

    public int getFax_pha() {
        return fax_pha;
    }

    public int getId_cal() {
        return id_cal;
    }

    public String getLat_gm_pha() {
        return lat_gm_pha;
    }

    public String getLong_gm_pha() {
        return long_gm_pha;
    }

    public String getEmail_pha() {
        return email_pha;
    }

    public int getType_pha() {
        return type_pha;
    }

    public int getVille_pha() {
        return ville_pha;
    }

    public int getGouv_pha() {
        return gouv_pha;
    }

    public void setId_pha(int id_pha) {
        this.id_pha = id_pha;
    }

    public void setId_resp(int id_resp) {
        this.id_resp = id_resp;
    }

    public void setNom_pha(String nom_pha) {
        this.nom_pha = nom_pha;
    }

    public void setAddresse_pha(String addresse_pha) {
        this.addresse_pha = addresse_pha;
    }

    public void setTel_pha(int tel_pha) {
        this.tel_pha = tel_pha;
    }

    public void setFax_pha(int fax_pha) {
        this.fax_pha = fax_pha;
    }

    public void setId_cal(int id_cal) {
        this.id_cal = id_cal;
    }

    public void setLat_gm_pha(String lat_gm_pha) {
        this.lat_gm_pha = lat_gm_pha;
    }

    public void setLong_gm_pha(String long_gm_pha) {
        this.long_gm_pha = long_gm_pha;
    }

    public void setEmail_pha(String email_pha) {
        this.email_pha = email_pha;
    }

    public void setType_pha(int type_pha) {
        this.type_pha = type_pha;
    }

    public void setVille_pha(int ville_pha) {
        this.ville_pha = ville_pha;
    }

    public void setGouv_pha(int gouv_pha) {
        this.gouv_pha = gouv_pha;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString, hashCode et equals">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id_pha;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pharmacie other = (Pharmacie) obj;
        if (this.id_pha != other.id_pha) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pharmacie{" + "id_pha=" + id_pha + ", id_resp=" + id_resp + ", nom_pha=" + nom_pha + ", addresse_pha=" + addresse_pha + ", tel_pha=" + tel_pha + ", fax_pha=" + fax_pha + ", id_cal=" + id_cal + ", lat_gm_pha=" + lat_gm_pha + ", long_gm_pha=" + long_gm_pha + ", email_pha=" + email_pha + ", type_pha=" + type_pha + ", ville_pha=" + ville_pha + ", gouv_pha=" + gouv_pha + '}';
    }

    // </editor-fold>
}
