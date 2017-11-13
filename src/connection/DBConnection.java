/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kalum
 */
public class DBConnection {

    private Connection connection;
    private static DBConnection dBConnection;
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Nisaco;";

    private DBConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(URL, "sa", "123");
        } catch (ClassNotFoundException ex) {
             System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getDBConnection() throws SQLException {
        if (dBConnection == null) {
            dBConnection = new DBConnection();
        } else if (dBConnection.getConnection().isClosed()) {
            dBConnection = new DBConnection();
        }
        return dBConnection;
    }
}
