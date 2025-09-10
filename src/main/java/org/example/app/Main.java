package org.example.app;

import org.example.dao.ClienteDao;
import org.example.dao.MotoristaDao;
import org.example.dao.PedidoDao;
import org.example.model.Cliente;
import org.example.model.Motorista;
import org.example.model.Pedido;

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

                break;
            }

            case 6: {
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