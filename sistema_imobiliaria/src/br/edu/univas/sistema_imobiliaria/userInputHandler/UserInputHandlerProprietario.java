package br.edu.univas.sistema_imobiliaria.userInputHandler;

import br.edu.univas.sistema_imobiliaria.Proprietario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserInputHandlerProprietario {
    public static Proprietario userInputHandlerProprietario(Scanner scanner, int type, int _cod){
        SimpleDateFormat _format = new SimpleDateFormat("yyyy-MM-dd");
        int codProprietario = 0;

        while(true){
            try{
                if (type == 1){codProprietario = _cod;}
                else{
                    System.out.println("Digite o código do Proprietario: ");
                    codProprietario = scanner.nextInt();
                    // Limpa o Buffer
                    scanner.nextLine();
                }

                System.out.println("Digite o nome do Proprietário: ");
                String nomeProprietario = scanner.next();

                // Limpa o Buffer
                scanner.nextLine();

                System.out.println("Digite o telefone do Proprietário: ");
                long telefoneProprietario = scanner.nextLong();

                // Limpa o Buffer
                scanner.nextLine();

                System.out.println("Digite o tipo de Proprietário: ");
                System.out.println("Fisica");
                System.out.println("Jurídica");
                String tipoProprietario = scanner.next();

                System.out.println("Digite o CPF: ");
                String cpfProprietario = scanner.nextLine();

                System.out.println("Digite o CNPJ(Caso Exista): ");
                String cnpjProprietario = scanner.nextLine();

                return new Proprietario(codProprietario, nomeProprietario, telefoneProprietario, tipoProprietario, cpfProprietario, cnpjProprietario);
            }
            catch (Exception e){
                System.out.println("Erro de input" + e.getMessage());
                scanner.nextLine();
                continue;
            }
        }
    }
    
}
