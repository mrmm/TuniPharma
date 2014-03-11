/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.tunipharma.classes.Evenement;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class EvenementsDAO {

    private Connection connexion;
    private Statement stmt;
    private static EvenementsDAO instance;

    private EvenementsDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static EvenementsDAO getInstance() {
        if (instance == null) {
            try {
                instance = new EvenementsDAO();
            } catch (SQLException ex) {
                Logger.getLogger(EvenementsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (instance);

    }

    public Evenement create(Evenement obj) {

        String sql = "INSERT INTO Evenements (id_pha ,date_event ,nom_event ,desc_event,etat_event)"
                + "VALUES"
                + "(?,?,?,?,?);";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId_pha());
            pstmt.setDate(2, (Date) obj.getDate_event());
            pstmt.setString(3, obj.getNom_event());
            pstmt.setString(4, obj.getDesc_event());
            pstmt.setBoolean(5, obj.isEtat_event());
            System.out.println("SQL EvenementsDAO - create : "+pstmt.toString());
            obj.setId_event(pstmt.executeUpdate());
            int last_inserted_id=-1;
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
            obj.setId_event(last_inserted_id);
            System.out.println("SQL EvenementsDAO - create - Info Evenement : " + obj);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<Evenement> readAll() {
        List< Evenement> l = new ArrayList<Evenement>();
        Evenement event;
        String sql = "SELECT * FROM Evenements";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Evenement(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                event = new Evenement(res.getInt(1), res.getInt(2), res.getDate(3), res.getString(4), res.getString(5), res.getBoolean(6));
                l.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Evenement readById(Integer id) {
        Evenement event = null;
        String sql = "SELECT * FROM Evenements WHERE id_event='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Evenement(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                event = new Evenement(res.getInt(1), res.getInt(2), res.getDate(3), res.getString(4), res.getString(5), res.getBoolean(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (event);
    }

    public void update(Evenement obj) {

        String sql;
        sql = "UPDATE Evenements SET "
                + "id_pha = '?',"
                + "date_event = '?',"
                + "nom_event = '?',"
                + "desc_event = '?',"
                + "etat_event = '?' "
                + "WHERE id_event = '" + obj.getId_event() + "';";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setInt(1, obj.getId_pha());
            pstmt.setDate(2, (Date) obj.getDate_event());
            pstmt.setString(3, obj.getNom_event());
            pstmt.setString(4, obj.getDesc_event());
            pstmt.setBoolean(5, obj.isEtat_event());
            pstmt.setInt(6, obj.getId_event());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql;
        sql = "DELETE FROM Evenements WHERE id_event = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteByIdPha(int id_pha) {
        String sql;
        sql = "DELETE FROM Evenements WHERE id_pha = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, id_pha);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void changeEtat(int id,boolean etat) {
        String sql = "UPDATE Comptes SET etat_event = ? "
                + "WHERE id_event =  ? ;";
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
