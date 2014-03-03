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
import pidev.tunipharma.classes.Ville;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class VillesDAO {

    private Connection connexion;
    private Statement stmt;
    private static VillesDAO instance;

    private VillesDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static VillesDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new VillesDAO();
        }
        return (instance);

    }

    public Ville create(Ville obj) throws Exception {
        throw (new Exception("Not Supported"));
    }

    public List<Ville> readAll() {
        List< Ville> l = new ArrayList<Ville>();
        Ville ville;
        String sql = "SELECT id_ville, nom_ville, id_gouv FROM Villes";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                ville = new Ville(res.getInt(1), res.getInt(3), res.getString(2));
                l.add(ville);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public List<Ville> readAllByIdGouv(int id_gouv) {
        List< Ville> l = new ArrayList<Ville>();
        Ville ville;
        String sql = "SELECT id_ville, nom_ville, id_gouv FROM Villes WHERE id_gouv=" + id_gouv + "";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                ville = new Ville(res.getInt(1), res.getInt(3), res.getString(2));
                l.add(ville);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Ville readById(Integer id) {
        Ville ville = null;
        String sql = "SELECT * FROM Villes WHERE id_ville ='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                ville = new Ville(res.getInt(1), res.getInt(2), res.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (ville);
    }

    public void update(Ville obj) throws Exception {
        throw (new Exception("Not Supported"));
    }

    public void delete(Ville obj) throws Exception {
        throw (new Exception("Not Supported"));
    }
}
