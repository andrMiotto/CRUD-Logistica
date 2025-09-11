package org.example.dao;

import org.example.model.Cliente;
import org.example.model.Motorista;
import org.example.model.Pedido;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDao {

    public Motorista inserirMotorista(Motorista motorista) throws SQLException {
        String query = "INSERT INTO motorista(nome,cnh,veiculo,cidade_base) VALUES (?,?,?,?)";

        try (Connection connection =  Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3, motorista.getVeiculo());
            stmt.setString(4, motorista.getCidade_base());

            stmt.executeUpdate();

        }

        return motorista;
    }

    public static List<Motorista> listarTodos() throws SQLException {
        String query = "SELECT id, nome FROM motorista";

        List<Motorista> motoristas = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Motorista motorista = new Motorista(id, nome);
                motoristas.add(motorista);
            }


        }

        return motoristas;

    }
}
