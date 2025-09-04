package org.example.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/logistica?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA = "mysqlPW";

    public static Conexao conectar() throws SQLException {
        return (Conexao) DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
