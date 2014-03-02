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
public class Ville {

    int id_ville;
    int id_gouv;
    String nom_ville;

    public Ville(int id_ville, int id_gouv, String nom_ville) {
        this.id_ville = id_ville;
        this.id_gouv = id_gouv;
        this.nom_ville = nom_ville;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public int getId_ville() {
        return id_ville;
    }

    public int getId_gouv() {
        return id_gouv;
    }

    public String getNom_ville() {
        return nom_ville;
    }

    public void setId_ville(int id_ville) {
        this.id_ville = id_ville;
    }

    public void setId_gouv(int id_gouv) {
        this.id_gouv = id_gouv;
    }

    public void setNom_ville(String nom_ville) {
        this.nom_ville = nom_ville;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="hashCode, Equals & toString">
    @Override
    public String toString() {
        return "Ville{" + "id_ville=" + id_ville + ", id_gouv=" + id_gouv + ", nom_ville=" + nom_ville + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id_ville;
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
        final Ville other = (Ville) obj;
        if (this.id_ville != other.id_ville) {
            return false;
        }
        return true;
    }
    //</editor-fold>

}
