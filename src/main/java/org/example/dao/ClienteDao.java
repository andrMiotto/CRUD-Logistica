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

    public static boolean excluirCliente(int clienteId) throws SQLException {
        try (Connection connection = Conexao.conectar()) {
            String checkClienteQuery = "SELECT id FROM cliente WHERE id = ?";
            
            try (PreparedStatement checkStmt = connection.prepareStatement(checkClienteQuery)) {
                checkStmt.setInt(1, clienteId);
                ResultSet rs = checkStmt.executeQuery();
                
                if (!rs.next()) {
                    System.out.println("Cliente não encontrado com ID: " + clienteId);
                    return false;
                }
            }

            String checkPedidosQuery = "SELECT COUNT(*) FROM pedido WHERE cliente_id = ?";
            try (PreparedStatement pedidosStmt = connection.prepareStatement(checkPedidosQuery)) {
                pedidosStmt.setInt(1, clienteId);
                ResultSet rs = pedidosStmt.executeQuery();
                
                if (rs.next()) {
                    int countPedidos = rs.getInt(1);
                    if (countPedidos > 0) {
                        System.out.println("Não é possível excluir o cliente. Existem " + countPedidos + " pedidos associados a este cliente.");
                        System.out.println("Primeiro exclua ou cancele os pedidos antes de excluir o cliente.");
                        return false;
                    }
                }
            }

            String deleteClienteQuery = "DELETE FROM cliente WHERE id = ?";
            try (PreparedStatement clienteStmt = connection.prepareStatement(deleteClienteQuery)) {
                clienteStmt.setInt(1, clienteId);
                int rowsAffected = clienteStmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    System.out.println("Cliente excluído com sucesso!");
                    return true;
                } else {
                    System.out.println("Erro ao excluir cliente.");
                    return false;
                }
            }
        }
    }

}

