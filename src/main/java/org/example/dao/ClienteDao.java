package org.example.dao;

import org.example.model.Cliente;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    public Cliente inserirCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente(nome,cpf_cnpj,endereco,cidade,estado) VALUES (?,?,?,?,?)";

        try (Connection conn = (Connection) Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf_cnpj());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());

            stmt.executeUpdate();

        }

        return cliente;
    }

    public static List<Cliente> listarTodos() throws SQLException {
        String query = "SELECT id, nome FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                var cliente = new Cliente(id, nome);
                clientes.add(cliente);
            }
        }

        return clientes;
    }

}

