package org.example.app;

import org.example.dao.*;
import org.example.model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        inicio();


    }

    public static void inicio() throws SQLException {
        boolean sair = false;

        System.out.println("=== LOGISTICA ===");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Motorista");
        System.out.println("3 - Criar Pedido");
        System.out.println("4 - Gerar Entrega");
        System.out.println("5 - Registrar Evento de Entrega");
        System.out.println("6 - Atualizar status de Entrega");


        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = SC.nextInt();
        SC.nextLine();

        switch (opcao) {
            case 1: {
                cadastrarCliente();

                break;
            }

            case 2: {
                cadastrarMotorista();

                break;
            }
            case 3: {
                inserirPedido();
                break;
            }
            case 4: {
                criarEntrega();
                break;
            }

            case 5: {
                registrarEvento();
                break;
            }

            case 6: {
                atualizarStatus();
                break;
            }

            case 0: {
                sair = true;
                break;
            }
        }

        if (!sair) {
            inicio();
        }


    }


    public static void cadastrarCliente() {
        System.out.println("=== CADASTRAR CLIENTE ===");

        inserirDadosCliente(1, 0);


    }

    public static void cadastrarMotorista() {
        System.out.println("=== CADASTRAR MOTORISTA ===");
        var dao = new MotoristaDao();

        System.out.println("Nome do motorista: ");
        String nome = SC.nextLine();


        System.out.println("CNH: ");
        String cnh = SC.nextLine();

        System.out.println("Veiculo utilizado: ");
        String veiculo = SC.nextLine();

        System.out.println("Cidade: ");
        String cidade_base = SC.nextLine();


        try {
            var motorista = new Motorista(nome, cnh, veiculo, cidade_base);
            dao.inserirMotorista(motorista);
            System.out.println("Motorista inserido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void inserirPedido() throws SQLException {
        System.out.println("=== CRIAR PEDIDO ===");
        var dao = new PedidoDao();
        var clienteDao = new ClienteDao();


        List<Cliente> listaClientes = ClienteDao.listarTodos();


        System.out.println("=--- LISTA DE CLIENTES ---=");
        for (Cliente c : listaClientes) {
            System.out.println(c.getId() + " - " + c.getNome());
        }

        System.out.println("Escolha o id do cliente:");
        int clienteId = SC.nextInt();

        System.out.println("Digite o volume em m3");
        Double volume_m3 = SC.nextDouble();

        System.out.println("Digite o peso em KG");
        Double peso_kg = SC.nextDouble();

        try {
            var pedido = new Pedido(clienteId, LocalDate.now(), volume_m3, peso_kg);
            dao.inserirPedido(pedido);
            System.out.println("Pedido inserido com sucesso!!!");
        } catch (SQLException e) {
            System.out.println("ERRO ao inserir pedido:");
            e.printStackTrace();
        }
    }


    public static void criarEntrega() throws SQLException {
        System.out.println("=== GERAR ENTREGA ===");
        var dao = new EntregaDao();
        var pedidoDao = new PedidoDao();
        var motoristaDao = new MotoristaDao();

        List<Pedido> listaPedidos = PedidoDao.listarTodos();

        System.out.println("=--- LISTA DE PEDIDOS ---=");
        for (Pedido p : listaPedidos) {
            System.out.println(p.getId() + " - " + p.getDataPedido());
        }

        System.out.println("Escolha o id do pedido:");
        int pedidoId = SC.nextInt();

        List<Motorista> listaMotoristas = MotoristaDao.listarTodos();

        System.out.println("=--- LISTA DE MOTORISTAS ---=");
        for (Motorista m : listaMotoristas) {
            System.out.println(m.getId() + " - " + m.getNome());
        }

        System.out.println("Escolha o id do motorista:");
        int motoristaId = SC.nextInt();

        try {
            Entrega entrega = new Entrega(pedidoId, motoristaId);
            dao.criarEntrega(entrega);
            System.out.println("Entrega gerada com sucesso!!!");
        } catch (SQLException e) {
            System.out.println("ERRO ao gerar entrega");
            e.printStackTrace();
        }


    }


    public static void registrarEvento() throws SQLException {
        System.out.println("=== GERAR EVENTO ===");

        var dao = new HistoricoDao();
        var entregaDao = new EntregaDao();

        List<Entrega> listaEntregas = EntregaDao.listarTodos();
        for(Entrega e: listaEntregas ){
            System.out.println(e.getId() + " - " + e.getPedidoId());
        }

        System.out.println("Escolha o id da entrega: ");
        int entrega_id = SC.nextInt();
        SC.nextLine();

        System.out.println("Digite a descricao do evento ocorrido: ");
        String descricao = SC.nextLine();

        try {
            Historico historico = new Historico(entrega_id,LocalDate.now(), descricao);
            dao.registrarHistorico(historico);
            System.out.println("Historico gerado com sucesso!!!");
        } catch (SQLException e) {
            System.out.println("ERRO ao gerar historico");
            e.printStackTrace();
        }

    }



    public static void atualizarStatus() throws SQLException{
        System.out.println("=== ATUALIZAR STATUS DE ENTREGA ===");

        var entregaDao = new EntregaDao();

        List<Entrega> listaEntregas = EntregaDao.listarTodos();
        for(Entrega e: listaEntregas ){
            System.out.println(e.getId() + " - " + e.getPedidoId());
        }

        System.out.println("Escolha o id da entrega: ");
        int entrega_id = SC.nextInt();
        SC.nextLine();

        System.out.println("Digite o novo status da entrega (EM_ROTA, ENTREGUE, ATRASADA): ");
        String novoStatus = SC.nextLine();

        try {
            StatusEntrega status = StatusEntrega.valueOf(novoStatus.toUpperCase());

            Entrega entrega = new Entrega();
            entrega.setId(entrega_id);
            entrega.setStatus(status);

            entregaDao.atualizarStatus(entrega);
            System.out.println("Entrega atualizada com sucesso!!!");
        } catch (IllegalArgumentException e) {
            System.out.println("Status inválido!");
        } catch (SQLException e) {
            System.out.println("ERRO ao atualizar entrega");
            e.printStackTrace();
        }

    }


    public static void inserirDadosCliente(int opcao, int id) {
        var dao = new ClienteDao();
        System.out.println("Nome do cliente: ");
        String nome = SC.nextLine();


        System.out.println("CPF/CNPJ: ");
        String cpf_cnpj = SC.nextLine();

        System.out.println("Endereço: ");
        String endereco = SC.nextLine();

        System.out.println("Cidade: ");
        String cidade = SC.nextLine();

        System.out.println("Estado: ");
        String estado = SC.nextLine();

        switch (opcao) {
            case 1 -> {
                try {
                    var cliente = new Cliente(nome, cpf_cnpj, endereco, cidade, estado);
                    dao.inserirCliente(cliente);
                    System.out.println("Cliente inserido com sucesso!!!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 2 -> {
                //atualizar
            }

        }
    }


}