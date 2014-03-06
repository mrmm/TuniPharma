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
import pidev.tunipharma.classes.Event;
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

    public static EvenementsDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new EvenementsDAO();
        }
        return (instance);

    }

    public Event create(Event obj) {

        String sql = "INSERT INTO Evenements (id_pha ,date_event ,nom_event ,desc_event,etat_event)"
                + "VALUES"
                + "(?,?,?,?,?);";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId_pha());
            pstmt.setDate(2, obj.getDate_event());
            pstmt.setString(3, obj.getNom_event());
            pstmt.setString(4, obj.getDesc_event());
            pstmt.setBoolean(5, obj.isEtat_event());

            obj.setId_pha(pstmt.executeUpdate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<Event> readAll() {
        List< Event> l = new ArrayList<Event>();
        Event event;
        String sql = "SELECT * FROM Evenements";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                event = new Event(res.getInt(1), res.getInt(2), res.getDate(3), res.getString(4), res.getString(5), res.getBoolean(6));
                l.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Event readById(Integer id) {
        Event event = null;
        String sql = "SELECT * FROM Evenements WHERE id_event='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                event = new Event(res.getInt(1), res.getInt(2), res.getDate(3), res.getString(4), res.getString(5), res.getBoolean(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (event);
    }

    public void update(Event obj) {

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
            pstmt.setDate(2, obj.getDate_event());
            pstmt.setString(3, obj.getNom_event());
            pstmt.setString(4, obj.getDesc_event());
            pstmt.setBoolean(5, obj.isEtat_event());
            pstmt.setInt(6, obj.getId_event());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Event obj) {
        String sql;
        sql = "DELETE FROM Evenements WHERE id_event = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_event());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
