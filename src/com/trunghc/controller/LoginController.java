package com.trunghc.controller;

import com.trunghc.connect.MySqlConnection;
import com.trunghc.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginController {

    public static Login Login(String username, String password) {
        ArrayList<Login> dsLogin = new ArrayList<Login>();
        Login login = null;
        try {
            Connection connection = MySqlConnection.getMySQLConnection();
            String sql = "select * from login where username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                login = new Login();
                login.setId(resultSet.getInt("id"));
                login.setUsername(resultSet.getString("username"));
                login.setPassword(resultSet.getString("password"));
                dsLogin.add(login);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return login;
    }
}
