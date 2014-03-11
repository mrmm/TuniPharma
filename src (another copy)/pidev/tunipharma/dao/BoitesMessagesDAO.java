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
import pidev.tunipharma.classes.BoiteMessages;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class BoitesMessagesDAO {
    
    private Connection connexion;
    private Statement stmt;
    private static BoitesMessagesDAO instance;

    private BoitesMessagesDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static BoitesMessagesDAO getInstance() {
        if (instance == null) {
            try {
                instance = new BoitesMessagesDAO();
            } catch (SQLException ex) {
                Logger.getLogger(BoitesMessagesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (instance);

    }

    public BoiteMessages create(BoiteMessages obj) {

        String sql = "INSERT INTO Boites_Message (id_cpt)"
                + "VALUES"
                + "(?);";
        
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId_cpt());

            obj.setId_bt(pstmt.executeUpdate());
            int last_inserted_id=-1;
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
            obj.setId_bt(last_inserted_id);
            System.out.println("SQL create - Info Boite Message : " + obj);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<BoiteMessages> readAll() {
        List< BoiteMessages> l = new ArrayList<BoiteMessages>();
        BoiteMessages bt_msg;
        String sql = "SELECT * FROM Boites_Message";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                bt_msg = new BoiteMessages(res.getInt(1), res.getInt(2));
                l.add(bt_msg);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public BoiteMessages readById(Integer id) {
        BoiteMessages bt_msg = null;
        String sql = "SELECT * FROM Boites_Message WHERE id_bt =" + id + "";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                bt_msg = new BoiteMessages(res.getInt(1), res.getInt(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (bt_msg);
    }

    public void update(BoiteMessages obj) {

        String sql;
        sql = "UPDATE Boites_Message SET "
                + "id_cpt = '?',"
                + "WHERE id_bt = '" + obj.getId_bt()+ "';";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setInt(1, obj.getId_cpt());
            pstmt.setInt(2, obj.getId_bt());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(BoiteMessages obj) {
        String sql;
        sql = "DELETE FROM Boites_Message WHERE id_bt = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_bt());
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteByIdCpt(int id_cpt) {
        String sql;
        sql = "DELETE FROM Boites_Message WHERE id_cpt = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, id_cpt);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
