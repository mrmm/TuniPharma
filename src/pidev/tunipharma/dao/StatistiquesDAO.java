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
import pidev.tunipharma.classes.Statistiques;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author Chiheb
 */
public class StatistiquesDAO {

    private Connection connexion;
    private Statement stmt;
    private static StatistiquesDAO instance;

    private StatistiquesDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static StatistiquesDAO getInstance() {
        if (instance == null) {
            try {
                instance = new StatistiquesDAO();
            } catch (SQLException ex) {
                Logger.getLogger(StatistiquesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (instance);

    }
    
    public Statistiques create(Statistiques obj) {

            String sql = "INSERT INTO statistiques(type_stat,valeur,id_pha)"
                + "VALUES"
                + "(?,?,?);";
        
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getType_stat());
            pstmt.setInt(2, obj.getValeur());
            pstmt.setInt(3, obj.getId_pha());

            pstmt.executeUpdate();
            int last_inserted_id=-1;
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
            obj.setId_stat(last_inserted_id);
            System.out.println("SQL create - Info Statistiques : " + obj);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }
    
    public List<Statistiques> readAll() {
        List<Statistiques> l = new ArrayList<Statistiques>();
        Statistiques stat;
        String sql = "SELECT * FROM Statistiquess";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //    public Statistiques(id_stat, type_stat, valeur, id_pha)
                stat = new Statistiques(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4));
                l.add(stat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }
    
    public Statistiques readById(Integer id) {
        Statistiques stat = null;
        String sql = "SELECT * FROM Statistiquess WHERE id_stat='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                // public Statistiques(id_stat, type_stat, valeur, id_pha)
                stat = new Statistiques(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (stat);
    }
    
    
    public void update(Statistiques obj) {
        String sql;

        sql = "UPDATE Statistiquess SET "
                + "type_stat = '?',"
                + "valeur = '?',"
                + "id_pha = '?',"
                + "WHERE id_stat = '" + obj.getId_stat()+ "';";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setInt(1, obj.getType_stat());
            pstmt.setInt(2, obj.getValeur());
            pstmt.setInt(3, obj.getId_pha());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void delete(Statistiques obj) {
        String sql;
        sql = "DELETE FROM Statistiques WHERE id_stat = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_stat());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
