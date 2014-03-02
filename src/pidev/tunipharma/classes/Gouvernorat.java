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
public class Gouvernorat {
    int id_gouv;
    String nom_gouv;

    public Gouvernorat(int id_gouv, String nom_gouv) {
        this.id_gouv = id_gouv;
        this.nom_gouv = nom_gouv;
    }
    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public int getId_gouv() {
        return id_gouv;
    }

    public String getNom_gouv() {
        return nom_gouv;
    }

    public void setId_gouv(int id_gouv) {
        this.id_gouv = id_gouv;
    }

    public void setNom_gouv(String nom_gouv) {
        this.nom_gouv = nom_gouv;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="hashCode, Equals & toString">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.id_gouv;
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
        final Gouvernorat other = (Gouvernorat) obj;
        if (this.id_gouv != other.id_gouv) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gouvernorat{" + "id_gouv=" + id_gouv + ", nom_gouv=" + nom_gouv + '}';
    }
    // </editor-fold>
    
    
}
