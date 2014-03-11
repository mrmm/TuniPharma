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
public class Vote {

    int id_vote;
    int id_cpt;
    int id_pha;
    int valeur_vote;

    public Vote(int id_vote, int id_cpt, int id_pha, int valeur_vote) {
        this.id_vote = id_vote;
        this.id_cpt = id_cpt;
        this.id_pha = id_pha;
        this.valeur_vote = valeur_vote;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">                          
    // Teste sur le boutton de la formulaire d'acceuil
    public int getId_vote() {
        return id_vote;
    }

    public int getId_cpt() {
        return id_cpt;
    }

    public int getId_pha() {
        return id_pha;
    }

    public int getValeur_vote() {
        return valeur_vote;
    }

    public void setId_vote(int id_vote) {
        this.id_vote = id_vote;
    }

    public void setId_cpt(int id_cpt) {
        this.id_cpt = id_cpt;
    }

    public void setId_pha(int id_pha) {
        this.id_pha = id_pha;
    }

    public void setValeur_vote(int valeur_vote) {
        this.valeur_vote = valeur_vote;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString, hashCode & equals">                          
    // Teste sur le boutton de la formulaire d'acceuil
    @Override
    public String toString() {
        return "Vote{" + "id_vote=" + id_vote + ", id_cpt=" + id_cpt + ", id_pha=" + id_pha + ", valeur_vote=" + valeur_vote + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id_vote;
        hash = 71 * hash + this.id_cpt;
        hash = 71 * hash + this.id_pha;
        hash = 71 * hash + this.valeur_vote;
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
        final Vote other = (Vote) obj;
        if (this.id_vote != other.id_vote) {
            return false;
        }
        if (this.id_cpt != other.id_cpt) {
            return false;
        }
        if (this.id_pha != other.id_pha) {
            return false;
        }
        if (this.valeur_vote != other.valeur_vote) {
            return false;
        }
        return true;
    }
    // </editor-fold>

}
