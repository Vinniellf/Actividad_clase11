package com.example.lab9_base.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/lab9";

        return DriverManager.getConnection(url, user, password);
    }

}
