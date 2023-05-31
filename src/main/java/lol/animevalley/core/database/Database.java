package lol.animevalley.core.database;

import lol.animevalley.core.MiniPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database extends MiniPlugin {

    private Connection conn;

    public Database() {
        super("Database");
        // Attempt to connect to the database
        say("Connecting to the database...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.0.0.114:3306/minecraft","minecraft","8Teh1XO4[.C/*NCl");
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

    @Override
    public void Startup() {
    }
}
