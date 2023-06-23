package cc.leafed.core.database;

import cc.leafed.core.Core;
import cc.leafed.core.MiniPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database extends MiniPlugin {

    private Connection conn;

    public Database() {
        super("Database");
        // Attempt to connect to the database
        String host = Core.getCore().getConfig().getString("database.host");
        String database = Core.getCore().getConfig().getString("database.data");
        String username = Core.getCore().getConfig().getString("database.username");
        String password = Core.getCore().getConfig().getString("database.password");
        say("Connecting to the database...");
        try {
            Class.forName("com.mysql.jdbc.Driver");// TODO: Add a way to change the port at some point in time
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database,username,password);
            say("Ready.");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet query(String sql) throws SQLException {
        return conn.createStatement().executeQuery(sql);
    }

    public int update(String sql) throws SQLException {
        return conn.createStatement().executeUpdate(sql);
    }
}
