package stef.projects.console.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/quiz_app";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection CONNECTION;

    private DatabaseConnection() {
    }

    private static Connection getConnection() {
        if (CONNECTION == null) {
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

    public static PreparedStatement getPreparedStatementFromQuery(String query) throws SQLException {
        return DatabaseConnection.getConnection().prepareStatement(query);
    }

    public static boolean extractStatus(int status) {
        if (status == 1) {
            return true;
        }
        return false;
    }
}
