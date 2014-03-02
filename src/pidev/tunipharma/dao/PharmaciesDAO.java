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
import pidev.tunipharma.classes.Pharmacie;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class PharmaciesDAO {

    private Connection connexion;
    private Statement stmt;
    private static PharmaciesDAO instance;

    private PharmaciesDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static PharmaciesDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new PharmaciesDAO();
        }
        return (instance);

    }

    public Pharmacie create(Pharmacie obj) {

        String sql;
        sql = "INSERT INTO Pharmacies "
                + "(id_resp,nom_pha,addresse_pha,tel_pha,fax_pha,id_cal,lat_gm_pha,long_gm_pha,email_pha,type_pha,ville_pha,gouv_pha)"
                + "VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId_resp());
            pstmt.setString(2, obj.getNom_pha());
            pstmt.setString(3, obj.getAddresse_pha());
            pstmt.setInt(4, obj.getTel_pha());
            pstmt.setInt(5, obj.getFax_pha());
            pstmt.setInt(6, obj.getId_cal());
            pstmt.setString(7, obj.getLat_gm_pha());
            pstmt.setString(8, obj.getLong_gm_pha());
            pstmt.setString(9, obj.getEmail_pha());
            pstmt.setInt(10, obj.getType_pha());
            pstmt.setString(11, obj.getVille_pha());
            pstmt.setString(12, obj.getGouv_pha());

            obj.setId_pha(pstmt.executeUpdate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<Pharmacie> readAll() {
        List< Pharmacie> l = new ArrayList<Pharmacie>();
        Pharmacie pha;
        String sql = "SELECT * FROM Pharmacies";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //    public Pharmacie(id_pha,    id_resp,      nom_pha,         addresse_pha,   tel_pha, fax_pha, id_cal, lat_gm_pha, long_gm_pha, email_pha, type_pha)
                pha = new Pharmacie(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getInt(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11), res.getString(12), res.getString(13));
                l.add(pha);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Pharmacie readById(Integer id) {
        Pharmacie pha = null;
        String sql = "SELECT * FROM Pharmacies WHERE id_pha='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                pha = new Pharmacie(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getInt(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11), res.getString(12), res.getString(13));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (pha);
    }

    public void update(Pharmacie obj) {
        String sql;
        //id_pha,id_resp,nom_pha,addresse_pha,tel_pha,fax_pha,id_cal,lat_gm_pha,long_gm_pha,email_pha,type_pha
        sql = "UPDATE Pharmacies SET "
                + "id_resp = '?',"
                + "nom_pha = '?',"
                + "addresse_pha = '?',"
                + "tel_pha = '?' "
                + "fax_pha = '?' "
                + "id_cal = '?' "
                + "lat_gm_pha = '?' "
                + "long_gm_pha = '?' "
                + "email_pha = '?' "
                + "type_pha = '?' "
                + "ville_pha = '?' "
                + "gouv_pha = '?' "
                + "WHERE id_event = '" + obj.getId_pha() + "';";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setInt(1, obj.getId_resp());
            pstmt.setString(2, obj.getNom_pha());
            pstmt.setString(3, obj.getAddresse_pha());
            pstmt.setInt(4, obj.getTel_pha());
            pstmt.setInt(5, obj.getFax_pha());
            pstmt.setInt(6, obj.getId_cal());
            pstmt.setString(7, obj.getLat_gm_pha());
            pstmt.setString(8, obj.getLong_gm_pha());
            pstmt.setString(9, obj.getEmail_pha());
            pstmt.setInt(10, obj.getType_pha());
            pstmt.setString(11, obj.getVille_pha());
            pstmt.setString(12, obj.getGouv_pha());
            pstmt.setInt(13, obj.getId_pha());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Pharmacie obj) {
        String sql;
        sql = "DELETE FROM Pharmacies WHERE id_pha = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_pha());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Pharmacie> readByTypeVilleGouvType(int type, String ville, String gouv) {
        List< Pharmacie> l = new ArrayList<Pharmacie>();
        Pharmacie pha;
        String sql = "SELECT * FROM Pharmacies WHERE "
                + "ville_pha=" + (ville != "" ? ville : "%")
                + "AND gouv_pha=" + (gouv != "" ? gouv : "%")
                + " AND type" + (type > -1 ? "=" + type : "!=-1");
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                pha = new Pharmacie(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getInt(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11), res.getString(12), res.getString(13));
                l.add(pha);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }
}
