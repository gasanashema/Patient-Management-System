package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL      = "jdbc:postgresql://localhost:5432/PMIS_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "disaster";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
