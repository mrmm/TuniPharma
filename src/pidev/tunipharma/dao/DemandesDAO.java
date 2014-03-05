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

    public static DemandesDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new DemandesDAO();
        }
        return (instance);

    }

    public Demande create(Demande obj) {

        String sql;
        sql = "INSERT INTO Demandes (id_type_dmd ,date_dmd ,id_cpt_dmd ,id_concerne_dmd)"
                + "VALUES"
                + "(?,?,?,?);";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getType_dmd());
            pstmt.setDate(2, obj.getDate_dmd());
            pstmt.setInt(3, obj.getId_cpt_dmd());
            pstmt.setInt(4, obj.getId_concerne_dmd());

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

    public List readByType(int type) {
        List<Demande> l = new ArrayList<Demande>();
        Demande dmd;
        String sql;
        Event ev;
        Compte cpt;
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

    public void update(Demande obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(Demande obj) {
        String sql;
        sql = "DELETE FROM Demandes WHERE id_dmd = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_dmd());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
