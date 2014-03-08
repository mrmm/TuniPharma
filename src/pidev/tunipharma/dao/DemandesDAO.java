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
import pidev.tunipharma.classes.*;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class DemandesDAO {

    private Connection connexion;
    private Statement stmt;
    private static DemandesDAO instance;

    private DemandesDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static DemandesDAO getInstance() {
        if (instance == null) {
            try {
                instance = new DemandesDAO();
            } catch (SQLException ex) {
                Logger.getLogger(DemandesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (instance);

    }

    public Demande create(Demande obj) {

        String sql;
        sql = "INSERT INTO Demandes (id_type_dmd ,date_dmd ,id_cpt_dmd ,id_concerne_dmd)"
                + "VALUES"
                + "(?,NOW( ),?,?);";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getType_dmd());
            //pstmt.setDate(2, obj.getDate_dmd());
            pstmt.setInt(2, obj.getId_cpt_dmd());
            pstmt.setInt(3, obj.getId_concerne_dmd());
            System.out.println("SQL create - Demmande DAO : "+pstmt.toString());

            obj.setId_dmd(pstmt.executeUpdate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<Demande> readAll() {
        List< Demande> l = new ArrayList<Demande>();
        Demande dmd;
        String sql = "SELECT * FROM Demandes";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Demande(int id_dmd,int type_dmd,Date date_dmd,int id_cpt_dmd,int id_concerne_dmd)
                dmd = new Demande(res.getInt(1), res.getInt(2), res.getDate(3), res.getInt(4), res.getInt(5));
                l.add(dmd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Demande readById(Integer id) {
        Demande dmd = null;
        String sql = "SELECT * FROM Demandes WHERE id_dmd='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                dmd = new Demande(res.getInt(1), res.getInt(2), res.getDate(3), res.getInt(4), res.getInt(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (dmd);
    }

    /**
     * @param type 1 pour les evenements et 2 pour les comptes
     * @return List<Demande> : les demande selon le type selectionné
     */
    public List<Demande> readByType(int type) {
        List<Demande> l = new ArrayList<Demande>();
        Demande dmd;
        String sql;
        sql = "SELECT * FROM Demandes WHERE id_type_dmd=" + type;
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Demande(int id_dmd,int type_dmd,Date date_dmd,int id_cpt_dmd,int id_concerne_dmd)
                dmd = new Demande(res.getInt(1), res.getInt(2), res.getDate(3), res.getInt(4), res.getInt(5));
                l.add(dmd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    /**
     * @param id ID Compte ou Evenement de la demande
     * @param type Type de la demande 1 pour les evenements 2 pour les comptes
     * @return
     */
    public Demande readByIdConcern(int id, int type) {
        Demande dmd = null;
        String sql = "SELECT * FROM Demandes WHERE id_type_dmd = " + type + " AND id_concerne_dmd = " + id;
        System.out.println("SQL readByIdConcern : "+sql);
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                dmd = new Demande(res.getInt(1), res.getInt(2), res.getDate(3), res.getInt(4), res.getInt(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (dmd);
    }

    public void update(Demande obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(Demande obj) {
        String sql;
        sql = "DELETE FROM Demandes WHERE id_dmd = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_dmd());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
