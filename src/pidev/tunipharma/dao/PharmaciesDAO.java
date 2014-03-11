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

    public static PharmaciesDAO getInstance() {
        if (instance == null) {
            try {
                instance = new PharmaciesDAO();
            } catch (SQLException ex) {
                Logger.getLogger(PharmaciesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (instance);

    }

    public Pharmacie create(Pharmacie obj) {

        String sql;
        sql = "INSERT INTO Pharmacies "
                + "(id_resp,nom_pha,addresse_pha,tel_pha,fax_pha,lat_gm_pha,long_gm_pha,email_pha,type_pha,ville_pha,gouv_pha)"
                + "VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?)";
        //System.out.println("SQL : " + sql);
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId_resp());
            pstmt.setString(2, obj.getNom_pha());
            pstmt.setString(3, obj.getAddresse_pha());
            pstmt.setInt(4, obj.getTel_pha());
            pstmt.setInt(5, obj.getFax_pha());
            pstmt.setString(6, obj.getLat_gm_pha());
            pstmt.setString(7, obj.getLong_gm_pha());
            pstmt.setString(8, obj.getEmail_pha());
            pstmt.setInt(9, obj.getType_pha());
            pstmt.setInt(10, obj.getVille_pha());
            pstmt.setInt(11, obj.getGouv_pha());

            System.out.println("SQL PharmaciesDAO - create: " + pstmt);
            obj.setId_pha(pstmt.executeUpdate());
            int last_inserted_id = -1;
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
            obj.setId_pha(last_inserted_id);
            System.out.println("SQL PharmaciesDAO - create - Info Pharmacies : " + obj);
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
                pha = new Pharmacie(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9), res.getInt(10), res.getInt(11), res.getInt(12));
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
                pha = new Pharmacie(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9), res.getInt(10), res.getInt(11), res.getInt(12));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (pha);
    }

    public Pharmacie readByIdResp(Integer id) {
        Pharmacie pha = null;
        String sql = "SELECT * FROM Pharmacies WHERE id_resp='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                pha = new Pharmacie(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9), res.getInt(10), res.getInt(11), res.getInt(12));
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
            pstmt.setString(6, obj.getLat_gm_pha());
            pstmt.setString(7, obj.getLong_gm_pha());
            pstmt.setString(8, obj.getEmail_pha());
            pstmt.setInt(9, obj.getType_pha());
            pstmt.setInt(10, obj.getVille_pha());
            pstmt.setInt(11, obj.getGouv_pha());
            pstmt.setInt(13, obj.getId_pha());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Pharmacie> readByTypeVilleGouvTypeNom(int type, int gouv, int ville, String nom) {
        List<Pharmacie> l = new ArrayList<Pharmacie>();
        Pharmacie pha;
        String sql = "SELECT * FROM Pharmacies WHERE "
                + "ville_pha" + (ville > 0 ? "=" + ville : "!=-1")
                + " AND gouv_pha" + (gouv > 0 ? "=" + gouv : "!=-1")
                + " AND nom_pha LIKE \"" + (!nom.isEmpty() ? nom + "%" : "%")
                + "\" AND type_pha" + (type > 0 ? "=" + type : "!=-1");
        System.out.println("SQL : " + sql);
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                pha = new Pharmacie(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9), res.getInt(10), res.getInt(11), res.getInt(12));
                l.add(pha);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public void delete(int id) {
        String sql;
        sql = "DELETE FROM Pharmacies WHERE id_pha = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
