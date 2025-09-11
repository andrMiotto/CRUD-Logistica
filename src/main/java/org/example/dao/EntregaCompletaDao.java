package org.example.dao;

import org.example.model.EntregaCompleta;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntregaCompletaDao {

    public List<EntregaCompleta> listarTudoEntregas() throws SQLException {

        String query="SELECT e.id AS entrega_id, e.pedido_id, c.nome AS cliente_nome" +
                "m.nome AS motorista_nome, e.status, e.data_saida, e.data_entrega" +
                "FROM entrega" +
                "JOIN pedido p ON e.pedido_id = p.id" +
                "JOIN cliente c ON p.cliente_id = c.id" +
                "JOIN motorista m ON e.motorista_id = m.id;";

        List<EntregaCompleta> lista = new ArrayList<>();

        try ( Connection connection = Conexao.conectar();
              PreparedStatement stmt = connection.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();



        }
        return listarTudoEntregas();
    }


}
