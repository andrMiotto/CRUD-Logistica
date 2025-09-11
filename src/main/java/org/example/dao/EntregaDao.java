package org.example.dao;

import org.example.model.Cliente;
import org.example.model.Entrega;
import org.example.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDao {

    public Entrega criarEntrega(Entrega entrega) throws SQLException {
        String query = "INSERT INTO entrega(pedido_id, motorista_id, data_saida, data_entrega, status) VALUES (?,?,?,?,?)";

        try (Connection conn = (Connection) Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, entrega.getPedidoId());
            stmt.setInt(2, entrega.getMotoristaId());
            stmt.setDate(3, java.sql.Date.valueOf(entrega.getData_saida()));

            if (entrega.getData_entrega() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(entrega.getData_entrega()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.setString(5, entrega.getStatus().name());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entrega.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }


        return entrega;

    }

    public static List<Entrega> listarTodos() throws SQLException {
        String query = "SELECT id,pedido_id FROM entrega";
        List<Entrega> entregas = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int pedido_id = rs.getInt("pedido_id");

                var entrega = new Entrega(id, pedido_id);
                entregas.add(entrega);
            }
        }

        return entregas;
    }

    public void atualizarStatus(Entrega entrega) throws SQLException {
        String query = "UPDATE entrega SET status = ? WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entrega.getStatus().name());
            stmt.setInt(2, entrega.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Status atualizado com sucesso!");
            } else {
                System.out.println("Nenhuma entrega encontrada com esse ID.");
            }
        }


    }
}