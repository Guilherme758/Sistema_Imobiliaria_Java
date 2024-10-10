package br.edu.univas.sistema_imobiliaria.userInputHandler;

import br.edu.univas.sistema_imobiliaria.ClienteCorretor;
import java.util.Scanner;

public class UserInputHandlerClienteCorretor {

    public static ClienteCorretor userInputHandlerClienteCorretor(Scanner scanner) {
        // Solicitar o código do cliente
        System.out.println("Código do Cliente:");
        int codCliente = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        // Solicitar o código do corretor
        System.out.println("Código do Corretor:");
        int codCorretor = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        // Retornar um novo objeto ClienteCorretor com os valores fornecidos
        return new ClienteCorretor(codCliente, codCorretor);
    }
}