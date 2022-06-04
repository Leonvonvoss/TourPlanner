package at.technikumwien.tourplanner.DAL.postgres;

import at.technikumwien.tourplanner.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB {

    private final String connectionString;
    private final String username;
    private final String password;

    public PostgresDB() {
        Configuration configuration = Configuration.Instance();
        this.connectionString = configuration.getProperty("connectionString");
        this.username = configuration.getProperty("username");
        this.password = configuration.getProperty("password");
    }

    private Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}