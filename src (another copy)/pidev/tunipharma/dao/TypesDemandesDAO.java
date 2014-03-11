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
import pidev.tunipharma.classes.TypeDemande;
import pidev.tunipharma.connection.DBConnection;

/**
 *
 * @author elron
 */
public class TypesDemandesDAO {

    private Connection connexion;
    private Statement stmt;
    private static TypesDemandesDAO instance;

    private TypesDemandesDAO() throws SQLException {
        connexion = DBConnection.getInstance();
        stmt = connexion.createStatement();
    }

    public static TypesDemandesDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new TypesDemandesDAO();
        }
        return (instance);

    }

    public TypeDemande create(TypeDemande obj) {

        String sql = "INSERT INTO Types_Demande (dmd_table)"
                + "VALUES"
                + "(?);";

        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, obj.getDmd_table());

            obj.setId_type_dmd(pstmt.executeUpdate());
            int last_inserted_id = -1;
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
            obj.setId_type_dmd(last_inserted_id);
            System.out.println("SQL TypesDemandeDAO - create - Info TypeDemande : " + obj);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (obj);
    }

    public List<TypeDemande> readAll() {
        List< TypeDemande> l = new ArrayList<TypeDemande>();
        TypeDemande t_dmd;
        String sql = "SELECT * FROM Types_Demande";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                t_dmd = new TypeDemande(res.getInt(1), res.getString(2));
                l.add(t_dmd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (l);
    }

    public TypeDemande readById(Integer id) {
        TypeDemande t_dmd = null;
        String sql = "SELECT * FROM Types_Demande WHERE id_type_dmd ='" + id + "'";
        try {
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                //public Message(int id_Message, int id_pha, Date date_Message, String nom_Message, String desc_Message, Boolean etat_Message) {
                t_dmd = new TypeDemande(res.getInt(1), res.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (t_dmd);
    }

    public void update(TypeDemande obj) {

        String sql;
        sql = "UPDATE Types_Demande SET "
                + "dmd_table = ?,"
                + "WHERE id_type_dmd = " + obj.getId_type_dmd() + ";";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);

            pstmt.setString(1, obj.getDmd_table());
            pstmt.setInt(2, obj.getId_type_dmd());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(TypeDemande obj) {
        String sql;
        sql = "DELETE FROM Types_Demande WHERE id_type_dmd = ?;";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, obj.getId_type_dmd());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
