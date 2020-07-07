package com.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConnection {
    private static Connection connection;
    public static Connection getSqlConnection() throws SQLException {
        if(connection == null){
            String url="jdbc:mysql://localhost:3306/test_db?autoReconnect=true&useSSL=false";
            connection = DriverManager.getConnection(url,"asifRcode","asifRcode");
        }
        return connection;

    }
}
