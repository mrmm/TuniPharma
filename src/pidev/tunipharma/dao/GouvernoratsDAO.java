/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev.tunipharma.classes.Gouvernorat;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class GouvernoratsDAO {

    private Connection connexion;
    private Statement stmt;
    private static GouvernoratsDAO instance;

    private GouvernoratsDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static GouvernoratsDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new GouvernoratsDAO();
        }
        return (instance);

    }

    public Gouvernorat create(Gouvernorat obj) throws Exception {
        throw (new Exception("Not Supported"));
    }

    public List<Gouvernorat> readAll() {
        List< Gouvernorat> l = new ArrayList<Gouvernorat>();
        Gouvernorat gouv;
        String sql = "SELECT * FROM Gouvernorats";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                gouv = new Gouvernorat(res.getInt(1), res.getString(2));
                l.add(gouv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Gouvernorat readById(Integer id) {
        Gouvernorat gouv = null;
        String sql = "SELECT * FROM Gouvernorats WHERE id_gouv ='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                gouv = new Gouvernorat(res.getInt(1), res.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (gouv);
    }

    public void update(Gouvernorat obj) throws Exception {
        throw (new Exception("Not Supported"));
    }

    public void delete(Gouvernorat obj) throws Exception {
        throw (new Exception("Not Supported"));
    }
}
