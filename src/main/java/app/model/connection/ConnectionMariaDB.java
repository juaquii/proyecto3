package app.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMariaDB {
    private static final String URL = "jdbc:mariadb://localhost:3306/hotelpancho"; // Updated database name
    private static final String USER = "juaqu"; // Updated username
    private static final String PASSWORD = "root"; // Updated password

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}