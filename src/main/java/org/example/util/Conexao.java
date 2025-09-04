package org.example.util;


import java.sql.DriverManager;

import java.sql.SQLException;

public class Connection {

    private static final String URL = "jdbc:mysql://localhost:3306/listaTelefonica?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA = "mysqlPW";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
