package br.edu.univas.sistema_imobiliaria.userInputHandler;

import br.edu.univas.sistema_imobiliaria.Contrato;
import br.edu.univas.sistema_imobiliaria.Corretor;
import java.util.*;

public class UserInputHandlerCorretor {
    public static Corretor userInputHandlerCorretor(Scanner scanner, int type, int _cod){
        while(true){
            try{
                System.out.println("Digite o código do Corretor: ");
                int cod = scanner.nextInt();
                scanner.nextLine();


                System.out.println("Digite o CPF do Corretor: ");
                String cpf = scanner.nextLine();

                System.out.println("Digite o Nome do Corretor: ");  
                String nome = scanner.nextLine();

                System.out.println("Digite o Telefone do Corretor: ");
                Long telefone = scanner.nextLong();

                scanner.nextLine();


                return new Corretor(cod, cpf, nome, telefone);
            }
            catch (Exception e){
                System.out.println("Erro de input" + e.getMessage());
                scanner.nextLine();
                continue;
            }
        }
    }    
}
