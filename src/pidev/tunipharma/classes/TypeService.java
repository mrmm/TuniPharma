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
public class TypeService {

    int id_type_srv;
    String nom_type_srv;
    String description_type_srv;

    public TypeService(int id_type_srv, String nom_type_srv, String description_type_srv) {
        this.id_type_srv = id_type_srv;
        this.nom_type_srv = nom_type_srv;
        this.description_type_srv = description_type_srv;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">                          
    // Teste sur le boutton de la formulaire d'acceuil
    public int getId_type_srv() {
        return id_type_srv;
    }

    public String getNom_type_srv() {
        return nom_type_srv;
    }

    public String getDescription_type_srv() {
        return description_type_srv;
    }

    public void setId_type_srv(int id_type_srv) {
        this.id_type_srv = id_type_srv;
    }

    public void setNom_type_srv(String nom_type_srv) {
        this.nom_type_srv = nom_type_srv;
    }

    public void setDescription_type_srv(String description_type_srv) {
        this.description_type_srv = description_type_srv;
    }
    // </editor-fold>
    
}
