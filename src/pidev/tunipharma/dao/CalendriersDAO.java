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
import pidev.tunipharma.classes.Calendrier;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class CalendriersDAO {

    private Connection connexion;
    private Statement stmt;
    private static CalendriersDAO instance;

    private CalendriersDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static CalendriersDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new CalendriersDAO();
        }
        return (instance);

    }

    public Calendrier create(Calendrier obj) {

        String sql;
        sql = "INSERT INTO Calendriers "
                + "(id_pha,heure,minute,jour,mois,annee)"
                + "VALUES "
                + "(?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId_pha());
            pstmt.setInt(2, obj.getHeure());
            pstmt.setInt(3, obj.getMinute());
            pstmt.setInt(4, obj.getJour());
            pstmt.setInt(5, obj.getMois());
            pstmt.setInt(6, obj.getAnnee());

            obj.setId_cal(pstmt.executeUpdate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<Calendrier> readAll() {
        List< Calendrier> l = new ArrayList<Calendrier>();
        Calendrier cal;
        String sql = "SELECT * FROM Calendriers";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //    public Calendrier(id_type_srv,nom_type_srv,description_type_srv)
                cal = new Calendrier(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4), res.getInt(5), res.getInt(6), res.getInt(7));
                l.add(cal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Calendrier readById(Integer id) {
        Calendrier cal = null;
        String sql = "SELECT * FROM Calendriers WHERE id_cal='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                cal = new Calendrier(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4), res.getInt(5), res.getInt(6), res.getInt(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (cal);
    }

    public void update(Calendrier obj) {
        String sql;

        sql = "UPDATE Calendriers SET "
                + "id_pha = '?',"
                + "heure = '?',"
                + "minute = '?',"
                + "jour = '?',"
                + "mois = '?',"
                + "annee = '?',"
                + "WHERE id_cal = '" + obj.getId_cal()+ "';";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setInt(1, obj.getId_pha());
            pstmt.setInt(2, obj.getHeure());
            pstmt.setInt(3, obj.getMinute());
            pstmt.setInt(4, obj.getJour());
            pstmt.setInt(5, obj.getMois());
            pstmt.setInt(6, obj.getAnnee());
            pstmt.setInt(7, obj.getId_cal());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Calendrier obj) {
        String sql;
        sql = "DELETE FROM Calendriers WHERE id_cal = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_cal());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
