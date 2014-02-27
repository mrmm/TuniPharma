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
import pidev.tunipharma.classes.Message;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class MessagesDAO {
    
    private Connection connexion;
    private Statement stmt;
    private static MessagesDAO instance;

    private MessagesDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static MessagesDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new MessagesDAO();
        }
        return (instance);

    }

    public Message create(Message obj) {

        String sql = "INSERT INTO Messages (id_src ,id_dst ,sujet_msg,corps_msg,date_msg,etat_msg)"
                + "VALUES"
                + "(?,?,?,?,?,?);";
        
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId_src());
            pstmt.setInt(2, obj.getId_dst());
            pstmt.setString(3, obj.getSujet_msg());
            pstmt.setString(4, obj.getCorps_msg());
            pstmt.setDate(5, obj.getDate_msg());
            pstmt.setBoolean(6, obj.isEtat_msg());

            obj.setId_msg(pstmt.executeUpdate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<Message> readAll() {
        List< Message> l = new ArrayList<Message>();
        Message msg;
        String sql = "SELECT * FROM Messages";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                msg = new Message(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getDate(6), res.getBoolean(7));
                l.add(msg);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Message readById(Integer id) {
        Message msg = null;
        String sql = "SELECT * FROM Messages WHERE id_mdg ='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                msg = new Message(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getDate(6), res.getBoolean(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (msg);
    }

    public void update(Message obj) {

        String sql;
        sql = "UPDATE Messages SET "
                + "id_src = '?',"
                + "id_dst = '?',"
                + "sujet_msg = '?',"
                + "corps_msg = '?',"
                + "date_msg = '?' "
                + "etat_msg = '?' "
                + "WHERE id_msg = '" + obj.getId_msg()+ "';";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setInt(1, obj.getId_src());
            pstmt.setInt(2, obj.getId_dst());
            pstmt.setString(3, obj.getSujet_msg());
            pstmt.setString(4, obj.getCorps_msg());
            pstmt.setDate(5, obj.getDate_msg());
            pstmt.setBoolean(6, obj.isEtat_msg());
            pstmt.setInt(7, obj.getId_msg());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Message obj) {
        String sql;
        sql = "DELETE FROM Messages WHERE id_msg = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_msg());
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
