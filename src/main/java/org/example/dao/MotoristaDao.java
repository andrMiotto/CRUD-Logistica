package org.example.dao;

import org.example.model.Cliente;
import org.example.model.Motorista;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MotoristaDao {

    public Motorista inserirMotorista(Motorista motorista) throws SQLException {
        String query = "INSERT INTO motorista(nome,cnh,veiculo,cidade_base) VALUES (?,?,?,?)";

        try (Connection conn = (Connection) Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1,motorista.getNome());
            stmt.setString(2,motorista.getCnh());
            stmt.setString(3,motorista.getVeiculo());
            stmt.setString(4,motorista.getCidade_base());

            stmt.executeUpdate();

        }

        return motorista;
    }
}
