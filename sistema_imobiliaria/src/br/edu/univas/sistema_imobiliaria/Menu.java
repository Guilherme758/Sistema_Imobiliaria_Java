package br.edu.univas.sistema_imobiliaria;

import java.util.Scanner;

import br.edu.univas.sistema_imobiliaria.userInputHandler.*;
import br.edu.univas.sistema_imobiliaria.excelManager.*;

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
                    menuContrato(scanner);
                    break;
                case 4:
                    menuProprietario(scanner);
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

    private static void menuProprietario(Scanner scanner) {
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

                ExcelManagerProprietario.readProprietario(cod);
                break;
            case 2:
                Proprietario proprietario = UserInputHandlerProprietario.userInputHandlerProprietario(scanner, 0, 0);
                ExcelManagerProprietario.insertProprietario(proprietario);
                break;
            case 3:
                System.out.print("Digite o código que deseja deletar (Digite -1 caso queria deletar todos os registros do arquivo): ");
                cod = scanner.nextInt();
                scanner.nextLine();

                ExcelManagerProprietario.deleteProprietario(cod);
                break;
            case 4:
                System.out.print("Digite o código que deseja atualizar: ");
                cod = scanner.nextInt();
                scanner.nextLine();

                proprietario = UserInputHandlerProprietario.userInputHandlerProprietario(scanner, 1, cod);
                ExcelManagerProprietario.updateProprietario(proprietario, cod);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

    }

    private static void menuCorretor() {
        System.out.println("Menu Corretor:");
    }

    private static void menuImovel() {
        System.out.println("Menu Imóvel:");
    }
}
