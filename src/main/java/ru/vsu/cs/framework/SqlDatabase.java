package ru.vsu.cs.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlDatabase {
    public static final SqlDatabase INSTANCE = new SqlDatabase();

    private Connection connection;

    private SqlDatabase() { }

    public void initialize() {
        String url = Configuration.INSTANCE.getString("database.url");
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement prepareStatement(String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
