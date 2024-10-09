package br.edu.univas.sistema_imobiliaria;

import java.util.Scanner;

import br.edu.univas.sistema_imobiliaria.excelManager.ExcelManagerCliente;
import br.edu.univas.sistema_imobiliaria.excelManager.ExcelManagerClienteCorretor;
import br.edu.univas.sistema_imobiliaria.excelManager.ExcelManagerContrato;
import br.edu.univas.sistema_imobiliaria.userInputHandler.UserInputHandlerCliente;
import br.edu.univas.sistema_imobiliaria.userInputHandler.UserInputHandlerClienteCorretor;
import br.edu.univas.sistema_imobiliaria.userInputHandler.UserInputHandlerContrato;

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
                    menuCliente(scanner);
                    break;
                case 2:
                    menuClienteCorretor(scanner);
                    break;
                case 3:
                    menuContrato(scanner);
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

    private static void menuCliente(Scanner scanner) {
        System.out.println("Qual ação deseja realizar: ");
        System.out.println("1 - Leitura");
        System.out.println("2 - Inserção");
        System.out.println("3 - Deletar");
        System.out.println("4 - Atualizar");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        int cod = 0;

        switch (opcao) {
            case 1:
                System.out.print("Digite o código que deseja consultar (Digite -1 caso queira consultar todos os registros do arquivo): ");
                cod = scanner.nextInt();
                scanner.nextLine();

                ExcelManagerCliente.readCliente(cod);
                break;
            case 2:
                Cliente cliente = UserInputHandlerCliente.userInputHandlerCliente(scanner, 0, 0);
                ExcelManagerCliente.insertCliente(cliente);
                break;
            case 3:
                System.out.print("Digite o código que deseja deletar (Digite -1 caso queira deletar todos os registros do arquivo): ");
                cod = scanner.nextInt();
                scanner.nextLine();

                ExcelManagerCliente.deleteCliente(cod);
                break;
            case 4:
                System.out.print("Digite o código que deseja atualizar: ");
                cod = scanner.nextInt();
                scanner.nextLine();

                cliente = UserInputHandlerCliente.userInputHandlerCliente(scanner, 1, cod);
                ExcelManagerCliente.updateCliente(cliente, cod);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void menuClienteCorretor(Scanner scanner) {
            System.out.println("Menu Cliente_Corretor:");
            System.out.println("1 - Inserir novo Cliente_Corretor");
            System.out.println("2 - Deletar Cliente_Corretor");
            System.out.println("3 - Atualizar Cliente_Corretor");
            System.out.println("4 - Consultar Cliente_Corretor");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Inserir ClienteCorretor
                    ClienteCorretor clienteCorretor = UserInputHandlerClienteCorretor.userInputHandlerClienteCorretor(scanner);
                    ExcelManagerClienteCorretor.exportDataToExcel(clienteCorretor);
                    break;

                case 2:
                    // Deletar ClienteCorretor
                    System.out.println("Digite o código do ClienteCorretor que deseja deletar (ou -1 para deletar todos):");
                    int codDeletar = scanner.nextInt();
                    ExcelManagerClienteCorretor.deleteClienteCorretor(1, codDeletar); // opção 1 para deletar por código
                    break;

                case 3:
                    // Atualizar ClienteCorretor
                    System.out.println("Digite o código do ClienteCorretor que deseja atualizar:");
                    int codAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    ClienteCorretor clienteCorretorAtualizado = UserInputHandlerClienteCorretor.userInputHandlerClienteCorretor(scanner);
                    ExcelManagerClienteCorretor.updateClienteCorretor(codAtualizar, clienteCorretorAtualizado);
                    break;

                case 4:
                    // Consultar ClienteCorretor
                    System.out.println("Digite o código do ClienteCorretor que deseja consultar (ou -1 para consultar todos):");
                    int codConsultar = scanner.nextInt();
                    ExcelManagerClienteCorretor.readClienteCorretor(1, codConsultar); // opção 1 para consultar por código
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        private static void menuContrato(Scanner scanner) {
        System.out.println("Qual ação deseja realizar: ");
        System.out.println("1 - Leitura");
        System.out.println("2 - Inserção");
        System.out.println("3 - Deletar");
        System.out.println("4 - Atualizar");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        int cod = 0;

        switch(opcao){
            case 1:
                System.out.print("Digite o código que deseja consultar (Digite -1 caso queira consultar todos os registros do arquivo): ");
                cod = scanner.nextInt();
                scanner.nextLine();

                ExcelManagerContrato.readContrato(cod);
                break;
            case 2:
                Contrato contrato = UserInputHandlerContrato.userInputHandlerContrato(scanner, 0, 0);
                ExcelManagerContrato.insertContrato(contrato);
                break;
            case 3:
                System.out.print("Digite o código que deseja deletar (Digite -1 caso queria deletar todos os registros do arquivo): ");
                cod = scanner.nextInt();
                scanner.nextLine();

                ExcelManagerContrato.deleteContrato(cod);
                break;
            case 4:
                System.out.print("Digite o código que deseja atualizar: ");
                cod = scanner.nextInt();
                scanner.nextLine();

                contrato = UserInputHandlerContrato.userInputHandlerContrato(scanner, 1, cod);
                ExcelManagerContrato.updateContrato(contrato, cod);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
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
