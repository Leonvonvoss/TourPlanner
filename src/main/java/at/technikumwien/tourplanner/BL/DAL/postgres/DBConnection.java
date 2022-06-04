package at.technikumwien.tourplanner.BL.DAL.postgres;

import at.technikumwien.tourplanner.config.Configuration;

import java.io.Closeable;
import java.sql.*;

public class DBConnection implements Closeable {

    private static DBConnection instance;
    private Connection connection;
    private String connectionString;
    private String username;
    private String password;

    public static DBConnection getInstance() {
        if(instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    /**
     * Loads the PostgreSql JDBC-driver
     * Don't forget to add the dependency in the pom.xml, like
     *         <dependency>
     *             <groupId>org.postgresql</groupId>
     *             <artifactId>postgresql</artifactId>
     *             <version>42.2.18.jre7</version>
     *         </dependency>
     */
    public DBConnection() {
        try {
            Configuration configuration = Configuration.Instance();
            this.connectionString = configuration.getProperty("connectionString");
            this.username = configuration.getProperty("username");
            this.password = configuration.getProperty("password");
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgresSQL JDBC driver not found");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        if(connection == null) {
            try {
                connection = DBConnection.getInstance().connect();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    /*
    ** Helper function for getConnection()
     */
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    public static boolean executeSQL(Connection connection, String sql) throws SQLException {
        return executeSql(connection, sql, false);
    }

    public boolean executeSQL(String sql) throws SQLException {
        return executeSql(getConnection(), sql, false);
    }

    public static boolean executeSql(Connection connection, String sql, boolean ignoreIfFails) throws SQLException {
        try ( Statement statement = connection.createStatement() ) {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            if( !ignoreIfFails )
                throw e;
            return false;
        }
    }

    @Override
    public void close() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            connection = null;
        }
    }
}
