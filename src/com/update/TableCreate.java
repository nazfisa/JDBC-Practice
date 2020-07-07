package com.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class TableCreate {
    public static void main(String[] args) {
        try {
            Connection connection = sqlConnection.getSqlConnection();
            String createUserTable = "create table user(id  int(3) primary key, name varchar(20), " +
                    "email varchar(20), country varchar(20), password varchar(20));";
            Statement statement = connection.createStatement();
            statement.execute(createUserTable);
            System.out.println("User table has been created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
