package org.example.dao;

import org.example.model.Pedido;
import org.example.model.StatusPedido;
import org.example.util.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    public Pedido inserirPedido(Pedido pedido) throws SQLException {
        String query = "INSERT INTO pedido(cliente_id, data_pedido, volume_m3, peso_kg, status) VALUES (?,?,?,?,?)";

        try (Connection conn = (Connection) Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pedido.getClienteId());
            stmt.setDate(2, java.sql.Date.valueOf(pedido.getDataPedido()));
            stmt.setDouble(3, pedido.getVolumeM3());
            stmt.setDouble(4, pedido.getPesoKg());
            stmt.setString(5, pedido.getStatus().name());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pedido.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }

        return pedido;
    }


    public static List<Pedido> listarTodos() throws SQLException {
        String query = "SELECT id, data_pedido FROM pedido";
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate dataPedido = rs.getDate("data_pedido").toLocalDate();
                Pedido pedido = new Pedido(id, dataPedido);
                pedidos.add(pedido);
            }

        }


        return pedidos;
    }


    public static List<Pedido> buscarPedidosPorCliente(String cpfCnpj) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        String query = "SELECT p.id, p.data_pedido, p.volume_m3, p.peso_kg, p.status " +
                "FROM pedido p " +
                "JOIN cliente c ON p.cliente_id = c.id " +
                "WHERE c.cpf_cnpj = ?;";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cpfCnpj);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pedido p = new Pedido();
                    p.setId(rs.getInt("id"));
                    p.setDataPedido(rs.getDate("data_pedido").toLocalDate());
                    p.setVolumeM3(rs.getDouble("volume_m3"));
                    p.setPesoKg(rs.getDouble("peso_kg"));
                    p.setStatus(StatusPedido.valueOf(rs.getString("status")));
                    pedidos.add(p);
                }
            }
        }

        return pedidos;
    }


    public static void cancelarPedido(int pedidoId) throws SQLException {
        String query = "UPDATE pedido SET status = 'CANCELADO' WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pedidoId);
            stmt.executeUpdate();
        }

    }


}



