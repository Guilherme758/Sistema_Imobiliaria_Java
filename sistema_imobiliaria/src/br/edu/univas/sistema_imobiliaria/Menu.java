package br.edu.univas.sistema_imobiliaria;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Menu de Interação com o Usuário:");
            System.out.println("1 - CRUD Cliente ");
            System.out.println("2 - CRUD Cliente_Corretor ");
            System.out.println("3 - CRUD Contrato ");
            System.out.println("4 - CRUD Proprietário ");
            System.out.println("5 - CRUD Corretor ");
            System.out.println("6 - CRUD Imóvel ");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuClienteCorretor();
                    break;
                case 3:
                    menuContrato();
                    break;
                case 4:
                    menuProprietario();
                    break;
                case 5:
                    menuCorretor();
                    break;
                case 6:
                    menuImovel();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void menuCliente() {
        System.out.println("Menu Cliente:");
    }

    private static void menuClienteCorretor() {
        System.out.println("Menu Cliente_Corretor:");
    }

    private static void menuContrato() {
        System.out.println("Menu Contrato:");
    }

    private static void menuProprietario() {
        System.out.println("Menu Proprietário:");
    }

    private static void menuCorretor() {
        System.out.println("Menu Corretor:");

    }

    private static void menuImovel() {
        System.out.println("Menu Imóvel:");

    }
}
