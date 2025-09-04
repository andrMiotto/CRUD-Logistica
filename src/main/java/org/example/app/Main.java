package org.example.app;

import java.util.Scanner;

public class Main {
    static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        inicio();



    }
    public static void inicio() {
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

                break;
            }

            case 2: {


                break;
            }
            case 3: {

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



}