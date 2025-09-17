package org.example.dao;

import org.example.model.Entrega;
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

        String query = "SELECT e.id AS entrega_id, e.pedido_id, c.nome AS cliente_nome" +
                "m.nome AS motorista_nome, e.status, e.data_saida, e.data_entrega" +
                "FROM entrega" +
                "JOIN pedido p ON e.pedido_id = p.id" +
                "JOIN cliente c ON p.cliente_id = c.id" +
                "JOIN motorista m ON e.motorista_id = m.id;";

        List<EntregaCompleta> lista = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();


        }
        return listarTudoEntregas();
    }


    public static List<EntregaCompleta> listarEntregasCM() throws SQLException {
        String query = """
                    SELECT e.id, m.nome AS motorista_nome
                    FROM entrega e
                    JOIN motorista m ON e.motorista_id = m.id;
                """;

        List<EntregaCompleta> lista = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EntregaCompleta entrega = new EntregaCompleta();
                entrega.setEntregaId(rs.getInt("id"));
                entrega.setMotoristaNome(rs.getString("motorista_nome"));
                lista.add(entrega);
            }
        }
        return lista;
    }


    public static List<EntregaCompleta> relatorioEntregasPorMotorista() throws SQLException {
        List<EntregaCompleta> lista = new ArrayList<>();

        String query = """
                    SELECT m.nome AS motorista_nome, COUNT(e.id) AS total_entregas
                    FROM entrega e
                    JOIN motorista m ON e.motorista_id = m.id
                    GROUP BY m.nome
                    ORDER BY total_entregas DESC;
                """;

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EntregaCompleta e = new EntregaCompleta();
                e.setMotoristaNome(rs.getString("motorista_nome"));
                e.setTotalEntregas(rs.getInt("total_entregas"));
                lista.add(e);
            }
        }

        return lista;
    }


    public static List<EntregaCompleta> relatorioClientesMaisEntregas() throws SQLException {
        List<EntregaCompleta> lista = new ArrayList<>();

        String query = "SELECT c.nome AS cliente_nome, SUM(p.volume_m3) AS volume_total " +
                "FROM entrega e " +
                "JOIN pedido p ON e.pedido_id = p.id " +
                "JOIN cliente c ON p.cliente_id = c.id " +
                "WHERE e.status = 'ENTREGUE' " +
                "GROUP BY c.nome " +
                "ORDER BY volume_total DESC;";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EntregaCompleta e = new EntregaCompleta();
                e.setClienteNome(rs.getString("cliente_nome"));
                e.setVolumeTotal(rs.getDouble("volume_total"));
                lista.add(e);
            }
        }
        return lista;

    }

    public static List<EntregaCompleta> relatorioPendentesEstado() throws SQLException {
        List<EntregaCompleta> lista = new ArrayList<>();
        String query = "SELECT c.estado AS estado, COUNT(p.id) AS total_pedidos " +
                "FROM pedido p " +
                "JOIN cliente c ON p.cliente_id = c.id " +
                "WHERE p.status = 'PENDENTE' " +
                "GROUP BY c.estado " +
                "ORDER BY total_pedidos DESC;";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EntregaCompleta e = new EntregaCompleta();
                e.setEstado(rs.getString("estado"));
                e.setTotalEntregas(rs.getInt("total_pedidos"));
                lista.add(e);
            }

        }

        return lista;
    }

    public static List<EntregaCompleta> relatorioEntregasAtrasadasCidade() throws SQLException {
        List<EntregaCompleta> relatorio = new ArrayList<>();

        String query = "SELECT c.cidade AS cidade, COUNT(e.id) AS total_entregas_atrasadas " +
                "FROM entrega e " +
                "JOIN pedido p ON e.pedido_id = p.id " +
                "JOIN cliente c ON p.cliente_id = c.id " +
                "WHERE e.status = 'ATRASADA' " +
                "GROUP BY c.cidade " +
                "ORDER BY total_entregas_atrasadas DESC;";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EntregaCompleta e = new EntregaCompleta();
                e.setCidade(rs.getString("cidade"));
                e.setTotal_entregas_atrasadas(rs.getInt("total_entregas_atrasadas"));
                relatorio.add(e);
            }
        }
        return relatorio;
    }


}
