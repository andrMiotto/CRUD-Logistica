package org.example.dao;

import org.example.model.Historico;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoricoDao {

    public Historico registrarHistorico(Historico historico) throws SQLException {
        String query = "INSERT INTO historico_entrega(id, entrega_id,data_evento,descricao) VALUES (?,?,?,?) ";

        try (
                Connection connection = Conexao.conectar();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, historico.getId());
            stmt.setInt(2, historico.getEntrega_id());
            stmt.setDate(3, java.sql.Date.valueOf(historico.getData_evento()));
            stmt.setString(4, historico.getDescricao());

            stmt.executeUpdate();

        }

        return historico;
    }


}
