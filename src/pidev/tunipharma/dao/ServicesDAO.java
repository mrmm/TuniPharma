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
import pidev.tunipharma.classes.Service;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class ServicesDAO {

    private Connection connexion;
    private Statement stmt;
    private static ServicesDAO instance;

    private ServicesDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static ServicesDAO getInstance() {
        if (instance == null) {
            try {
                instance = new ServicesDAO();
            } catch (SQLException ex) {
                Logger.getLogger(ServicesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (instance);

    }

    public Service create(Service obj) {

        String sql;
        sql = "INSERT INTO Services "
                + "(id_pha,id_type_srv,description_srv,nom_srv)"
                + "VALUES "
                + "(?,?,?,?)";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId_pha());
            pstmt.setInt(2, obj.getId_type_srv());
            pstmt.setString(3, obj.getDescription_srv());
            pstmt.setString(4, obj.getNom_srv());

            obj.setId_srv(pstmt.executeUpdate());
            int last_inserted_id=-1;
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
            obj.setId_srv(last_inserted_id);
            System.out.println("SQL ServicesDAO - create - Info Service : " + obj);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<Service> readAll() {
        List< Service> l = new ArrayList<Service>();
        Service srv;
        String sql = "SELECT * FROM Services";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //    public Service(id_pha,    id_resp,      nom_pha,         addresse_pha,   tel_pha, fax_pha, id_cal, lat_gm_pha, long_gm_pha, email_pha, type_pha)
                srv = new Service(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5));
                l.add(srv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public Service readById(Integer id) {
        Service srv = null;
        String sql = "SELECT * FROM Services WHERE id_srv='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                srv = new Service(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (srv);
    }

    public void update(Service obj) {
        String sql;

        sql = "UPDATE Services SET "
                + "id_pha = '?',"
                + "id_type_srv = '?',"
                + "description_srv = '?' "
                + "nom_srv = '?' "
                + "WHERE id_srv = '" + obj.getId_srv() + "';";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setInt(1, obj.getId_pha());
            pstmt.setInt(2, obj.getId_type_srv());
            pstmt.setString(3, obj.getDescription_srv());
            pstmt.setString(4, obj.getNom_srv());
            pstmt.setInt(5, obj.getId_srv());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Service obj) {
        String sql;
        sql = "DELETE FROM Services WHERE id_srv = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_srv());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
