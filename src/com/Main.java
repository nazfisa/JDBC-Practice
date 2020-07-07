package com;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/test_db?autoReconnect=true&useSSL=false";

        String  s ="INSERT INTO users(name,password,email) VALUES ('Zara', 'Ali','jara@gmail.com');";
//        String  s ="Delete from users where name='Zara';";
//        String s = "update users set name='Zahim' where name ='Zara' ";


        try {

            Connection connection = DriverManager.getConnection(url,"asifRcode","asifRcode");
            Statement statement = connection.createStatement();
            statement.execute(s);
            ResultSet result = statement.executeQuery("Select * from users");


            while (result.next()){
                String ss= result.getString("name");
                System.out.println(ss);

            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
