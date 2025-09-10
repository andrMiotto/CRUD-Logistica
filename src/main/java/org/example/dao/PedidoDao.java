package org.example.dao;

import org.example.model.Pedido;
import org.example.model.StatusPedido;
import org.example.util.Conexao;

import java.sql.*;
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


}
