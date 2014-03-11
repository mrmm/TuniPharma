
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ElRon
 */
public class DBConnection {

//    $mysql_host = "mysql6.000webhost.com";
//    $mysql_database = "a8131179_pidev";
//    $mysql_user = "a8131179_pidev";
//    $mysql_password = "xinit2013";
    private String url;
    private String user;
    private String password;
    private static Connection cnx;

    private DBConnection() {
        try {
            url = "jdbc:mysql://localhost:3306/tunipharma";
            user = "root";
            password = "root";
            cnx = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getInstance() {
        if (cnx == null) {
            new DBConnection();
        }
        return cnx;
    }

}
