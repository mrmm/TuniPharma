/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.classes;

import java.util.Objects;

/**
 *
 * @author elron
 */
public class Service {

    int id_srv;
    int id_pha;
    int id_type_srv;
    String description_srv;
    String nom_srv;

    public Service(int id_srv, int id_pha, int id_type_srv, String description_srv, String nom_srv) {
        this.id_srv = id_srv;
        this.id_pha = id_pha;
        this.id_type_srv = id_type_srv;
        this.description_srv = description_srv;
        this.nom_srv = nom_srv;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">                          
    // Teste sur le boutton de la formulaire d'acceuil
    public int getId_srv() {
        return id_srv;
    }

    public int getId_pha() {
        return id_pha;
    }

    public int getId_type_srv() {
        return id_type_srv;
    }

    public String getDescription_srv() {
        return description_srv;
    }

    public String getNom_srv() {
        return nom_srv;
    }

    public void setId_srv(int id_srv) {
        this.id_srv = id_srv;
    }

    public void setId_pha(int id_pha) {
        this.id_pha = id_pha;
    }

    public void setId_type_srv(int id_type_srv) {
        this.id_type_srv = id_type_srv;
    }

    public void setDescription_srv(String description_srv) {
        this.description_srv = description_srv;
    }

    public void setNom_srv(String nom_srv) {
        this.nom_srv = nom_srv;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString, hashCode & equals">
    // Teste sur le boutton de la formulaire d'acceuil
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id_srv;
        hash = 23 * hash + this.id_pha;
        hash = 23 * hash + this.id_type_srv;
        hash = 23 * hash + Objects.hashCode(this.description_srv);
        hash = 23 * hash + Objects.hashCode(this.nom_srv);
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
        final Service other = (Service) obj;
        if (this.id_srv != other.id_srv) {
            return false;
        }
        if (this.id_pha != other.id_pha) {
            return false;
        }
        if (this.id_type_srv != other.id_type_srv) {
            return false;
        }
        if (!Objects.equals(this.description_srv, other.description_srv)) {
            return false;
        }
        if (!Objects.equals(this.nom_srv, other.nom_srv)) {
            return false;
        }
        return true;
    }

// </editor-fold>
}
