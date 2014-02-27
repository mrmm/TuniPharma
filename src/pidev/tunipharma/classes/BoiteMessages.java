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
    // Teste sur le boutton de la formulaire d'acceuil

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

}
