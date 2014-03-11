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
public class BoiteMessages {

    int id_bt;
    int id_cpt;

    public BoiteMessages(int id_bt, int id_cpt) {
        this.id_bt = id_bt;
        this.id_cpt = id_cpt;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public int getId_bt() {
        return id_bt;
    }

    public int getId_cpt() {
        return id_cpt;
    }

    public void setId_bt(int id_bt) {
        this.id_bt = id_bt;
    }

    public void setId_cpt(int id_cpt) {
        this.id_cpt = id_cpt;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="hashCode, Equals & toString">
    @Override
    public String toString() {
        return "BoiteMessages{" + "id_bt=" + id_bt + ", id_cpt=" + id_cpt + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.id_bt;
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
        final BoiteMessages other = (BoiteMessages) obj;
        if (this.id_bt != other.id_bt) {
            return false;
        }
        return true;
    }
    // </editor-fold>
    
}
