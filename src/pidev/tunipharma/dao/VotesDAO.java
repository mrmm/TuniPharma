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
import pidev.tunipharma.classes.Vote;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class VotesDAO {

    private Connection connexion;
    private Statement stmt;
    private static VotesDAO instance;

    private VotesDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static VotesDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new VotesDAO();
        }
        return (instance);

    }

    public Vote create(Vote obj) {

        String sql;
        sql = "INSERT INTO Votes "
                + "(id_cpt,id_pha,valeur_vote)"
                + "VALUES "
                + "(?,?,?)";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId_cpt());
            pstmt.setInt(2, obj.getId_pha());
            pstmt.setInt(3, obj.getValeur_vote());

            obj.setId_vote(pstmt.executeUpdate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<Vote> readAll() {
        List< Vote> l = new ArrayList<Vote>();
        Vote vote;
        String sql = "SELECT * FROM Votes";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //    public Vote(id_vote,nom_vote,description_vote)
                vote = new Vote(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4));
                l.add(vote);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Vote readById(Integer id) {
        Vote vote = null;
        String sql = "SELECT * FROM Votes WHERE id_vote='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                vote = new Vote(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (vote);
    }

    public void update(Vote obj) {
        String sql;

        sql = "UPDATE Votes SET "
                + "id_cpt = '?',"
                + "id_pha = '?',"
                + "valeur_vote = '?',"
                + "WHERE id_vote = '" + obj.getId_vote() + "';";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setInt(1, obj.getId_cpt());
            pstmt.setInt(2, obj.getId_pha());
            pstmt.setInt(3, obj.getValeur_vote());
            pstmt.setInt(4, obj.getId_vote());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Vote obj) {
        String sql;
        sql = "DELETE FROM Votes WHERE id_vote = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_vote());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
