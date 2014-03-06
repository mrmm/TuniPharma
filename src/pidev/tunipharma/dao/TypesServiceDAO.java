/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.dao;

import pidev.tunipharma.classes.TypeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class TypesServiceDAO {

    private Connection connexion;
    private Statement stmt;
    private static TypesServiceDAO instance;

    private TypesServiceDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static TypesServiceDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new TypesServiceDAO();
        }
        return (instance);

    }

    public TypeService create(TypeService obj) {

        String sql;
        sql = "INSERT INTO Types_Service "
                + "(nom_type_srv,description_type_srv)"
                + "VALUES "
                + "(?,?)";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, obj.getNom_type_srv());
            pstmt.setString(2, obj.getDescription_type_srv());

            obj.setId_type_srv(pstmt.executeUpdate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<TypeService> readAll() {
        List< TypeService> l = new ArrayList<TypeService>();
        TypeService type_srv;
        String sql = "SELECT * FROM Types_Service";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //    public TypeService(id_type_srv,nom_type_srv,description_type_srv)
                type_srv = new TypeService(res.getInt(1), res.getString(2), res.getString(3));
                l.add(type_srv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public TypeService readById(Integer id) {
        TypeService type_srv = null;
        String sql = "SELECT * FROM Types_Service WHERE id_type_srv='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Event(int id_event, int id_pha, Date date_event, String nom_event, String desc_event, Boolean etat_event) {
                type_srv = new TypeService(res.getInt(1), res.getString(2), res.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (type_srv);
    }

    public void update(TypeService obj) {
        String sql;

        sql = "UPDATE Types_Service SET "
                + "nom_type_srv = '?',"
                + "description_type_srv = '?',"
                + "WHERE id_type_srv = '" + obj.getId_type_srv() + "';";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setString(1, obj.getNom_type_srv());
            pstmt.setString(2, obj.getDescription_type_srv());
            pstmt.setInt(3, obj.getId_type_srv());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(TypeService obj) {
        String sql;
        sql = "DELETE FROM Types_Service WHERE id_type_srv = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_type_srv());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
