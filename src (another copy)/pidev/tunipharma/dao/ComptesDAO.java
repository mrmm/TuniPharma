/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.tunipharma.classes.Compte;
import pidev.tunipharma.classes.Demande;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class ComptesDAO {

    private Connection connexion;
    private Statement stmt;
    private static ComptesDAO instance;

    private ComptesDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static ComptesDAO getInstance() {
        if (instance == null) {
            try {
                instance = new ComptesDAO();
            } catch (SQLException ex) {
                Logger.getLogger(ComptesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (instance);

    }

    public Compte create(Compte obj) {

        String sql;
        sql = "INSERT INTO Comptes "
                + " (nom_cpt,prenom_cpt,email_cpt,pass_cpt,addresse_cpt,tel_cpt,type_cpt,etat_cpt)"
                + " VALUES "
                + " (?,?,?,md5(?),?,?,?,?)";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, obj.getNom_cpt());
            pstmt.setString(2, obj.getPrenom_cpt());
            pstmt.setString(3, obj.getEmail_cpt());
            pstmt.setString(4, obj.getPass_cpt());
            pstmt.setString(5, obj.getAddresse_cpt());
            pstmt.setInt(6, obj.getTel_cpt());
            pstmt.setInt(7, obj.getType_cpt());
            pstmt.setBoolean(8, obj.isEtat_cpt());

            pstmt.executeUpdate();

            int last_inserted_id = -1;
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
            obj.setId_cpt(last_inserted_id);

            System.out.println("SQL ComptesDAO - create - Info Compte : " + obj);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<Compte> readAll() {
        List<Compte> l = new ArrayList<Compte>();
        Compte ad;
        String sql = "SELECT id_cpt,nom_cpt,prenom_cpt,email_cpt,pass_cpt,addresse_cpt,tel_cpt,type_cpt,etat_cpt FROM Comptes;";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Compte( id_cpt,  nom_cpt,  prenom_cpt,  addresse_cpt,  email_cpt,  tel_cpt,  type_cpt,  etat_cpt,  pass_cpt) 
                ad = new Compte(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getBoolean(9));
                l.add(ad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public List<Compte> readAllPharmacienDisp() {
        List< Compte> l = new ArrayList<Compte>();
        Compte ad;
        String sql = "SELECT c.id_cpt,c.nom_cpt,c.prenom_cpt,c.email_cpt,c.pass_cpt,c.addresse_cpt,c.tel_cpt,c.type_cpt,c.etat_cpt FROM Comptes c WHERE c.type_cpt = " + Compte.COMPTE_PHARMACIEN
                + " AND c.id_cpt NOT IN (SELECT p.id_resp FROM Pharmacies p) ;";
        System.out.println("Req SQL readAllPharmacienDisp : " + sql);
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Compte( id_cpt,  nom_cpt,  prenom_cpt,  addresse_cpt,  email_cpt,  tel_cpt,  type_cpt,  etat_cpt,  pass_cpt) 
                ad = new Compte(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getBoolean(9));
                l.add(ad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public List<Compte> readAllClient() {
        List< Compte> l = new ArrayList<Compte>();
        Compte ad;
        String sql = "SELECT id_cpt,nom_cpt,prenom_cpt,email_cpt,pass_cpt,addresse_cpt,tel_cpt,type_cpt,etat_cpt FROM Comptes"
                + "WHERE type_cpt = 3";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Compte( id_cpt,  nom_cpt,  prenom_cpt,  addresse_cpt,  email_cpt,  tel_cpt,  type_cpt,  etat_cpt,  pass_cpt) 
                ad = new Compte(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getBoolean(9));
                l.add(ad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Compte readById(Integer id) {
        Compte cpt = null;
        String sql = "SELECT id_cpt,nom_cpt,prenom_cpt,addresse_cpt,email_cpt,pass_cpt,tel_cpt,type_cpt,etat_cpt "
                + "FROM Comptes WHERE id_cpt='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                cpt = new Compte(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getBoolean(9));;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(cpt);
        return (cpt);
    }

    public Compte readByEmail(String email) {
        Compte cpt = null;
        String sql = "SELECT id_cpt,nom_cpt,prenom_cpt,addresse_cpt,email_cpt,pass_cpt,tel_cpt,type_cpt,etat_cpt "
                + "FROM Comptes WHERE UPPER(email_cpt)='" + email.toUpperCase() + "'";
        try {
            System.out.println("SQL readByEmail - ComptesDAO : "+sql);
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                cpt = new Compte(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getBoolean(9));;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(cpt);
        return (cpt);
    }

    public List<Compte> readByNomPreType(Integer type, String nom, String prenom) {
        List< Compte> l = new ArrayList<Compte>();
        Compte cpt = null;
        String sql = "SELECT * FROM Comptes WHERE "
                + " nom_cpt LIKE \"" + (!nom.isEmpty() ? nom + "%" : "%") + "\""
                + " AND prenom_cpt LIKE \"" + (!prenom.isEmpty() ? prenom + "%" : "%") + "\""
                + " AND type_cpt" + (type > 0 ? "=" + type : "!=-1") + " "
                + " AND etat_cpt=1;";
        System.out.println("Req SQL : " + sql);
        try {
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                cpt = new Compte(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getBoolean(9));;
//                 System.out.println(cpt.toString());
                l.add(cpt);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public List<Compte> readInactif() {
        List< Compte> l = new ArrayList<Compte>();
        Compte cpt = null;
        String sql = "SELECT * FROM Comptes c WHERE c.etat_cpt = 0 AND "
                + "(SELECT COUNT(*) FROM Demandes d WHERE d.id_concerne_dmd = c.id_cpt AND d.id_cpt_dmd = c.id_cpt AND d.id_type_dmd=" + Demande.DEMANDE_COMPTE + " ) > 0;";
        System.out.println("Req SQL : " + sql);
        try {
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                cpt = new Compte(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getBoolean(9));;
//                 System.out.println(cpt.toString());
                l.add(cpt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public List<Compte> readActif() {
        List< Compte> l = new ArrayList<Compte>();
        Compte cpt = null;
        String sql = "SELECT * FROM Comptes WHERE etat_cpt=1;";
        System.out.println("Req SQL : " + sql);
        try {
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                cpt = new Compte(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getBoolean(9));;
//                 System.out.println(cpt.toString());
                l.add(cpt);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public void update(Compte obj) {
        String sql;
        sql = "UPDATE Comptes SET "
                + "nom_cpt = ?,"
                + "prenom_cpt = ?,"
                + "email_cpt = ?,"
                + "pass_cpt = md5(?),"
                + "addresse_cpt = ? ,"
                + "tel_cpt = ? ,"
                + "type_cpt = ? ,"
                + "etat_cpt = ? "
                + "WHERE id_cpt = " + obj.getId_cpt() + " ;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setString(1, obj.getNom_cpt());
            pstmt.setString(2, obj.getPrenom_cpt());
            pstmt.setString(3, obj.getEmail_cpt());
            pstmt.setString(4, obj.getPass_cpt());
            pstmt.setString(5, obj.getAddresse_cpt());
            pstmt.setInt(6, obj.getTel_cpt());
            pstmt.setInt(7, obj.getType_cpt());
            pstmt.setBoolean(8, obj.isEtat_cpt());
            System.out.println("Update Compte SQL : " + pstmt.toString());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Comptes WHERE id_cpt = ? ;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, id);
            System.out.println("SQL Delete Compte : " + pstmt);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changeEtat(int id, boolean etat) {
        String sql = "UPDATE Comptes SET etat_cpt = ? "
                + "WHERE id_cpt =  ? ;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setBoolean(1, etat);
            pstmt.setInt(2, id);
            System.out.println("SQL Activate Compte : " + pstmt);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
