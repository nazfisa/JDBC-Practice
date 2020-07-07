package com.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    public static void main(String[] args) {
        User userOne = new User(1, "Jahangir", "jahangir@gmail.com", "Bangladesh", "12345");
        User userTwo = new User(2, "Abir", "Abir@bs-23.com", "Bangladesh", "12345");
        User userThree = new User(3, "Mim", "Mim@bs-23.com", "Bangladesh", "12345");
        User userFour = new User(4, "Wahid", "Wahid@bs-23.com", "Bangladesh", "12345");
        User userFive = new User(5, "Rajesh", "Rajesh@bs-23.com", "Bangladesh", "12345");
        Map<Integer, User> users = new HashMap<>();
        users.put(userOne.getId(), userOne);
        users.put(userTwo.getId(), userTwo);
        users.put(userThree.getId(), userThree);
        users.put(userFour.getId(), userFour);
        users.put(userFive.getId(), userFive);
        UserManager userManager = new UserManager();
        userManager.insertStudent(users);
//        studentManager.insertBatch(new ArrayList(users.values()));
//        studentManager.updateUser(userOne);
//        studentManager.deleteUser(userOne.getId());
        userManager.showUsers();


    }
    public void insertStudent(Map<Integer, User> users) {
        try {
            Connection connection = sqlConnection.getSqlConnection();
            users.forEach((id, user) -> {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into user (id, name, email, country, password)" +
                            " VALUES(?, ?, ?, ?, ?); ");

                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.setString(2, user.getName());
                    preparedStatement.setString(3, user.getEmail());
                    preparedStatement.setString(4, user.getCountry());
                    preparedStatement.setString(5, user.getPassword());

                    preparedStatement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertBatch(List<User> users) {
        try {
            Connection connection = sqlConnection.getSqlConnection();
            connection.setAutoCommit(false);
            String query = "insert into user (id, name, email, country, password) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (User user : users) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getCountry());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String query = "update user set email=? where id = ?";
        try {
            PreparedStatement preparedStatement = sqlConnection.getSqlConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String query = "delete from user where id=?";
        try {
            PreparedStatement preparedStatement = sqlConnection.getSqlConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showUsers() {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = sqlConnection.getSqlConnection().prepareStatement("select * from user;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("user: " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }
