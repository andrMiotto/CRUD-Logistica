package org.example.app;

import org.example.dao.*;
import org.example.model.*;

import java.sql.SQLException;
import java.time.LocalDate;
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
        System.out.println("7 - Listar Todas as Entregas com Cliente e Motorista");
        System.out.println("8 - Relatório: Total de Entregas por Motorista");
        System.out.println("9 - Relatório: Clientes com Maior Volume Entregue");
        System.out.println("10 - Relatório: Pedidos Pendentes por Estado");
        System.out.println("11 - Relatório: Entregas Atrasadas por Cidade");
        System.out.println("12 - Buscar Pedido por CPF/CNPJ do Cliente");
        System.out.println("13 - Cancelar Pedido");
        System.out.println("14 - Excluir Entrega");
        System.out.println("15 - Excluir Cliente");
        System.out.println("16 - Excluir Motorista");


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

            case 7: {
                listarEntregasCM();
                break;
            }

            case 8: {
                relEntregasPorMotorista();
                break;

            }

            case 9: {
                relatorioClientesMaisEntregas();
                break;
            }
            case 10: {
                relatorioPendentesEstado();
                break;
            }

            case 11: {
                relatorioEntregasAtrasadasCidade();
                break;
            }

            case 12: {
                buscarPedidoPorCpfCnpj();
                break;
            }

            case 13: {
                cancelarPedido();
                break;
            }

            case 14: {
                excluirEntrega();
                break;
            }

            case 15: {
                excluirCliente();
                break;
            }

            case 16: {
                excluirMotorista();
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
            var pedido = new Pedido();
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
        for (Entrega e : listaEntregas) {
            System.out.println(e.getId() + " - " + e.getPedidoId());
        }

        System.out.println("Escolha o id da entrega: ");
        int entrega_id = SC.nextInt();
        SC.nextLine();

        System.out.println("Digite a descricao do evento ocorrido: ");
        String descricao = SC.nextLine();

        try {
            Historico historico = new Historico(entrega_id, LocalDate.now(), descricao);
            dao.registrarHistorico(historico);
            System.out.println("Historico gerado com sucesso!!!");
        } catch (SQLException e) {
            System.out.println("ERRO ao gerar historico");
            e.printStackTrace();
        }

    }


    public static void atualizarStatus() throws SQLException {
        System.out.println("=== ATUALIZAR STATUS DE ENTREGA ===");

        var entregaDao = new EntregaDao();

        List<Entrega> listaEntregas = EntregaDao.listarTodos();
        for (Entrega e : listaEntregas) {
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


    public static void listarEntregasCM() throws SQLException {
        var entregaCDAO = new EntregaCompletaDao();

        List<EntregaCompleta> listaEntregaCM = EntregaCompletaDao.listarEntregasCM();
        System.out.println("ID entrega | Nome do motorista");
        System.out.println("-------------------------------");
        for (EntregaCompleta e : listaEntregaCM) {
            System.out.println(e.getEntregaId() + " - " + e.getMotoristaNome());
        }
    }


    public static void relEntregasPorMotorista() throws SQLException {
        List<EntregaCompleta> relatorio = EntregaCompletaDao.relatorioEntregasPorMotorista();

        System.out.println("Relatório: Total de Entregas por Motorista");
        System.out.println("------------------------------------------");

        for (EntregaCompleta e : relatorio) {
            System.out.println(e.getMotoristaNome() + " -> " + e.getTotalEntregas() + " entregas");
        }

    }


    public static void relatorioClientesMaisEntregas() throws SQLException {
        List<EntregaCompleta> relatorio = EntregaCompletaDao.relatorioClientesMaisEntregas();
        System.out.println("Relatório: Cliente com maior numero de entregas");
        System.out.println("------------------------------------------");

        if (relatorio.isEmpty()) {
            System.out.println("Nenhuma entrega concluída no momento.");
        } else {
            for (EntregaCompleta e : relatorio) {
                System.out.println(e.getClienteNome() + " -> " + e.getVolumeTotal() + " m³");
            }
        }

    }


    public static void relatorioPendentesEstado() throws SQLException {
        List<EntregaCompleta> relatorio = EntregaCompletaDao.relatorioPendentesEstado();
        System.out.println("Relatório: Pedidos Pendentes por Estado");
        System.out.println("------------------------------------------");

        for (EntregaCompleta e : relatorio) {
            System.out.println(e.getEstado() + "->" + e.getTotalEntregas());
        }

    }


    public static void relatorioEntregasAtrasadasCidade() throws SQLException {
        List<EntregaCompleta> relatorio = EntregaCompletaDao.relatorioEntregasAtrasadasCidade();
        System.out.println("Relatório: Entregas Atrasadas por Cidade");
        System.out.println("------------------------------------------");

        for (EntregaCompleta e : relatorio) {
            System.out.println(e.getCidade() + " -> " + e.getTotal_entregas_atrasadas());
        }


    }


    public static void buscarPedidoPorCpfCnpj() throws SQLException {


        List<Cliente> clientes = ClienteDao.listarTodos();

        System.out.println("=--- LISTA DE CLIENTES ---=");
        for (Cliente c : clientes) {
            System.out.println(c.getId() + " - " + c.getNome());
        }

        System.out.println("Escolha o id do cliente:");
        int clienteId = SC.nextInt();

        Cliente clienteSelecionado = null;
        for (Cliente c : clientes) {
            if (c.getId() == clienteId) {
                clienteSelecionado = c;
                break;
            }
        }

        List<Pedido> pedidos = PedidoDao.buscarPedidosPorCliente(clienteSelecionado.getCpf_cnpj());
        System.out.println("=== Pedidos do Cliente " + clienteSelecionado.getNome() + " ===");
        System.out.println("ID | Data | Volume(m3) | Peso(kg) | Status");
        System.out.println("------------------------------------------");
        for (Pedido p : pedidos) {
            System.out.println(p.getId() + " | " +
                    p.getDataPedido() + " | " +
                    p.getVolumeM3() + " | " +
                    p.getPesoKg() + " | " +
                    p.getStatus());
        }

    }

    public static void cancelarPedido() throws SQLException {
        var pedidoDao = new PedidoDao();

        List<Pedido> listaPedidos = PedidoDao.listarTodos();

        System.out.println("=--- LISTA DE PEDIDOS ---=");
        for (Pedido p : listaPedidos) {
            System.out.println(p.getId() + " - " + p.getDataPedido());
        }

        System.out.print("Digite o ID do pedido que deseja cancelar: ");
        int pedidoId = SC.nextInt();
        SC.nextLine();

        PedidoDao.cancelarPedido(pedidoId);

        System.out.println("Pedido cancelado com sucesso!");

    }


    public static void excluirEntrega() throws SQLException {
        System.out.println("=== EXCLUIR ENTREGA ===");
        
        List<Entrega> listaEntregas = EntregaDao.listarTodos();
        
        if (listaEntregas.isEmpty()) {
            System.out.println("Nenhuma entrega encontrada.");
            return;
        }
        
        System.out.println("=--- LISTA DE ENTREGAS ---=");
        for (Entrega e : listaEntregas) {
            System.out.println(e.getId() + " - Pedido ID: " + e.getPedidoId());
        }

        System.out.print("Digite o ID da entrega que deseja excluir: ");
        int entrega_id = SC.nextInt();
        SC.nextLine();
        
        try {
            boolean sucesso = EntregaDao.excluirEntrega(entrega_id);
            if (!sucesso) {
                System.out.println("Falha ao excluir entrega.");
            }
        } catch (SQLException e) {
            System.out.println("ERRO ao excluir entrega:");
            e.printStackTrace();
        }
    }


    public static void excluirCliente() throws SQLException {
        System.out.println("=== EXCLUIR CLIENTE ===");
        
        List<Cliente> listaClientes = ClienteDao.listarTodos();
        
        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }
        
        System.out.println("=--- LISTA DE CLIENTES ---=");
        for (Cliente c : listaClientes) {
            System.out.println(c.getId() + " - " + c.getNome());
        }

        System.out.print("Digite o ID do cliente que deseja excluir: ");
        int cliente_id = SC.nextInt();
        SC.nextLine();
        
        try {
            boolean sucesso = ClienteDao.excluirCliente(cliente_id);
            if (!sucesso) {
                System.out.println("Falha ao excluir cliente.");
            }
        } catch (SQLException e) {
            System.out.println("ERRO ao excluir cliente:");
            e.printStackTrace();
        }
    }

    public static void excluirMotorista() throws SQLException {
        System.out.println("=== EXCLUIR MOTORISTA ===");
        
        List<Motorista> listaMotoristas = MotoristaDao.listarTodos();
        
        if (listaMotoristas.isEmpty()) {
            System.out.println("Nenhum motorista encontrado.");
            return;
        }
        
        System.out.println("=--- LISTA DE MOTORISTAS ---=");
        for (Motorista m : listaMotoristas) {
            System.out.println(m.getId() + " - " + m.getNome());
        }

        System.out.print("Digite o ID do motorista que deseja excluir: ");
        int motorista_id = SC.nextInt();
        SC.nextLine();
        
        try {
            boolean sucesso = MotoristaDao.excluirMotorista(motorista_id);
            if (!sucesso) {
                System.out.println("Falha ao excluir motorista.");
            }
        } catch (SQLException e) {
            System.out.println("ERRO ao excluir motorista:");
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
            }

        }
    }


}