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

    public static boolean excluirMotorista(int motoristaId) throws SQLException {
        try (Connection connection = Conexao.conectar()) {

            String checkMotoristaQuery = "SELECT id FROM motorista WHERE id = ?";
            
            try (PreparedStatement checkStmt = connection.prepareStatement(checkMotoristaQuery)) {
                checkStmt.setInt(1, motoristaId);
                ResultSet rs = checkStmt.executeQuery();
                
                if (!rs.next()) {
                    System.out.println("Motorista não encontrado com ID: " + motoristaId);
                    return false;
                }
            }

            String checkEntregasQuery = "SELECT COUNT(*) FROM entrega WHERE motorista_id = ?";
            try (PreparedStatement entregasStmt = connection.prepareStatement(checkEntregasQuery)) {
                entregasStmt.setInt(1, motoristaId);
                ResultSet rs = entregasStmt.executeQuery();
                
                if (rs.next()) {
                    int countEntregas = rs.getInt(1);
                    if (countEntregas > 0) {
                        System.out.println("Não é possível excluir o motorista. Existem " + countEntregas + " entrega associada a este motorista.");
                        System.out.println("Primeiro exclua ou finalize as entregas antes de excluir o motorista.");
                        return false;
                    }
                }
            }

            String deleteMotoristaQuery = "DELETE FROM motorista WHERE id = ?";
            try (PreparedStatement motoristaStmt = connection.prepareStatement(deleteMotoristaQuery)) {
                motoristaStmt.setInt(1, motoristaId);
                int rowsAffected = motoristaStmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    System.out.println("Motorista excluído com sucesso!");
                    return true;
                } else {
                    System.out.println("Erro ao excluir motorista.");
                    return false;
                }
            }
        }
    }
}
