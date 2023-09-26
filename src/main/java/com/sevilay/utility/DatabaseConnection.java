package com.sevilay.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import static org.postgresql.core.ConnectionFactory.openConnection;

public class DatabaseConnection {
    //private constructora başka sınıftan ulaşamayız
    private static DatabaseConnection instance;
    public static final String dbName = "futbolappDB";
    public static final String url = " jdbc:postgresql://localhost:5432/" + dbName;
    public static final String password = "root";
    private static Connection connection;

    private DatabaseConnection() {

    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static Connection getConnection() throws Exception {
//        if(connection == null){
        try {
            connection = DriverManager.getConnection(url, dbName, password);
        } catch (SQLException e) {
            throw new Exception(e);
        }
//        }
        return connection;
    }


}
