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
public class TypeDemande{
    int id_type_dmd;
    String dmd_table;

    public TypeDemande(int id_type_dmd, String dmd_table) {
        this.id_type_dmd = id_type_dmd;
        this.dmd_table = dmd_table;
    }

    public int getId_type_dmd() {
        return id_type_dmd;
    }

    public String getDmd_table() {
        return dmd_table;
    }

    public void setId_type_dmd(int id_type_dmd) {
        this.id_type_dmd = id_type_dmd;
    }

    public void setDmd_table(String dmd_table) {
        this.dmd_table = dmd_table;
    }

    @Override
    public String toString() {
        return "TypesDemandesDAO{" + "id_type_dmd=" + id_type_dmd + ", dmd_table=" + dmd_table + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id_type_dmd;
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
        final TypeDemande other = (TypeDemande) obj;
        if (this.id_type_dmd != other.id_type_dmd) {
            return false;
        }
        return true;
    }

    
}
