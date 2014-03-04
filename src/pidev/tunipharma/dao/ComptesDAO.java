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
import pidev.tunipharma.classes.Compte;
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

    public static ComptesDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new ComptesDAO();
        }
        return (instance);

    }

    public Compte create(Compte obj) {

        String sql;
        sql = "INSERT INTO Comptes "
                + "(nom_cpt,prenom_cpt,email_cpt,pass_cpt,addresse_cpt,tel_cpt,type_cpt,etat_cpt)"
                + "VALUES "
                + "(?,?,?,md5(?),?,?,?,?)";
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

            obj.setId_cpt(pstmt.executeUpdate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<Compte> readAll() {
        List< Compte> l = new ArrayList<Compte>();
        Compte ad;
        String sql = "SELECT * FROM Comptes";
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
        String sql = "SELECT * FROM Comptes WHERE id_cpt='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                cpt = new Compte(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getBoolean(9));;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (cpt);
    }

    public List< Compte> readByNomPreType(Integer type, String nom, String prenom) {
        List< Compte> l = new ArrayList<Compte>();
        Compte cpt = null;
        String sql = "SELECT * FROM Comptes WHERE "
                + " AND nom_cpt = '" + (nom != "" ? nom : "%") + "'"
                + " AND prenom_cpt = '" + (prenom != "" ? prenom : "%") + "'"
                + " type_cpt" + (type > -1 ? "=" + type : "!=-1") + " ;";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                cpt = new Compte(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getBoolean(9));;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public void update(Compte obj) {
        String sql;
        sql = "UPDATE Comptes SET "
                + "nom_cpt = '?',"
                + "prenom_cpt = '?',"
                + "email_cpt = '?',"
                + "pass_cpt = '?',"
                + "addresse_cpt = '?' "
                + "tel_cpt = '?' "
                + "type_cpt = '?' "
                + "etat_cpt = '?' "
                + "WHERE id_event = '" + obj.getId_cpt() + "';";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setInt(1, obj.getId_cpt());
            pstmt.setString(2, obj.getNom_cpt());
            pstmt.setString(3, obj.getPrenom_cpt());
            pstmt.setString(4, obj.getEmail_cpt());
            pstmt.setString(5, obj.getPass_cpt());
            pstmt.setString(6, obj.getAddresse_cpt());
            pstmt.setInt(7, obj.getTel_cpt());
            pstmt.setInt(8, obj.getType_cpt());
            pstmt.setBoolean(9, obj.isEtat_cpt());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Compte obj) {
        String sql;
        sql = "DELETE FROM Comptes WHERE id_cpt = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_cpt());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
