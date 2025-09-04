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

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, pedido.getClienteId());
            stmt.setDate(2, pedido.getDataPedido()); 
            stmt.setDouble(3, pedido.getVolumeM3());
            stmt.setDouble(4, pedido.getPesoKg());
            stmt.setString(5, pedido.getStatus().name()); 

            stmt.executeUpdate();



        }

        return pedido;
    }


}
