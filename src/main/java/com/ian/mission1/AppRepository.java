package com.ian.mission1;

import java.sql.*;
import java.util.ArrayList;

public class AppRepository {
    private static final String DB_URL = "jdbc:sqlite:identifier.sqlite";

    // SQLite 연결
    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(DB_URL);
        if (conn != null) {
            System.out.println("Connected to database");
        }
        return conn;
    }

}