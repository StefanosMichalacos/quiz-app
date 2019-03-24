package stef.projects.console.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/quiz_app";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection CONNECTION;

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        if (CONNECTION == null){
            try {
                CONNECTION = initializeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return CONNECTION;
    }

    private static Connection initializeConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

    }

}
