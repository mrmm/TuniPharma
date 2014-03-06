/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.classes;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import pidev.tunipharma.utils.ImageImplement;

/**
 *
 * @author elron
 */
public class Compte {

    int id_cpt;
    String nom_cpt;
    String prenom_cpt;
    String addresse_cpt;
    String email_cpt;
    String pass_cpt;
    int tel_cpt;
    int type_cpt;
    boolean etat_cpt;

    public Compte(int id_cpt, String nom_cpt, String prenom_cpt, String addresse_cpt, String email_cpt, String pass_cpt, int tel_cpt, int type_cpt, boolean etat_cpt) {
        this.id_cpt = id_cpt;
        this.nom_cpt = nom_cpt;
        this.prenom_cpt = prenom_cpt;
        this.addresse_cpt = addresse_cpt;
        this.email_cpt = email_cpt;
        this.pass_cpt = pass_cpt;
        this.tel_cpt = tel_cpt;
        this.type_cpt = type_cpt;
        this.etat_cpt = etat_cpt;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public void setId_cpt(int id_cpt) {
        this.id_cpt = id_cpt;
    }

    public void setNom_cpt(String nom_cpt) {
        this.nom_cpt = nom_cpt;
    }

    public void setPrenom_cpt(String prenom_cpt) {
        this.prenom_cpt = prenom_cpt;
    }

    public void setAddresse_cpt(String addresse_cpt) {
        this.addresse_cpt = addresse_cpt;
    }

    public void setEmail_cpt(String email_cpt) {
        this.email_cpt = email_cpt;
    }

    public void setTel_cpt(int tel_cpt) {
        this.tel_cpt = tel_cpt;
    }

    public void setType_cpt(int type_cpt) {
        this.type_cpt = type_cpt;
    }

    public void setPass_cpt(String pass_cpt) {
        this.pass_cpt = pass_cpt;
    }

    public int getId_cpt() {
        return id_cpt;
    }

    public String getNom_cpt() {
        return nom_cpt;
    }

    public String getPrenom_cpt() {
        return prenom_cpt;
    }

    public String getAddresse_cpt() {
        return addresse_cpt;
    }

    public String getEmail_cpt() {
        return email_cpt;
    }

    public int getTel_cpt() {
        return tel_cpt;
    }

    public int getType_cpt() {
        return type_cpt;
    }

    public boolean isEtat_cpt() {
        return etat_cpt;
    }

    public String getPass_cpt() {
        return pass_cpt;
    }
    // </editor-fold> 

    public String getTypeCptNom() {
        switch (type_cpt) {
            case 1:
                return "Administrateur";
            case 2:
                return "Pharmacien";
            case 3:
                return "Client";
            default:
                return "Inconnu";
        }
    }

    public static JPanel getImageCpt(int type_c) {

        String nomImg = "";
        String path = "img/";
        switch (type_c) {
            case 1:
                nomImg = path + "admin.png";
                break;
            case 2:
                nomImg = path + "pharmacien.png";
                break;
            case 3:
                nomImg = path + "utilisateur.png";
                break;
            default:
                nomImg = path + "deconnecter.png";
                break;
        }
        System.out.println(nomImg);
        return (new ImageImplement(new ImageIcon(nomImg).getImage()));
    }

    public void activerCompte() {
        this.etat_cpt = true;
    }

    public void desactiverCompte() {
        this.etat_cpt = false;
    }

    @Override
    public String toString() {
        return "Compte{" + "id_cpt=" + id_cpt + ", nom_cpt=" + nom_cpt + ", prenom_cpt=" + prenom_cpt + ", addresse_cpt=" + addresse_cpt + ", email_cpt=" + email_cpt + ", pass_cpt=" + pass_cpt + ", tel_cpt=" + tel_cpt + ", type_cpt=" + type_cpt + ", etat_cpt=" + etat_cpt + '}';
    }
}
