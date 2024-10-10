package br.edu.univas.sistema_imobiliaria.userInputHandler;

import br.edu.univas.sistema_imobiliaria.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserInputHandlerContrato { 
    public static Contrato userInputHandlerContrato(Scanner scanner, int type, int _cod){
        SimpleDateFormat _format = new SimpleDateFormat("yyyy-MM-dd");
        int codContrato = 0;

        while(true){
            try{
                if (type == 1){codContrato = _cod;}
                else{
                    System.out.println("Digite o código do contrato: ");
                    codContrato = scanner.nextInt();
                    // Limpa o Buffer
                    scanner.nextLine();
                }

                System.out.println("Digite o código do cliente: ");
                int codCliente = scanner.nextInt();

                // Limpa o Buffer
                scanner.nextLine();

                System.out.println("Digite o código do Imóvel: ");
                int codImovel = scanner.nextInt();

                // Limpa o Buffer
                scanner.nextLine();

                System.out.println("Digite a data do contrato (Padrão yyyy-mm-dd): ");

                Date dataContrato = null;
                try{
                    dataContrato = _format.parse(scanner.nextLine());
                }
                catch (ParseException e){
                    System.out.println("O valor inserido não é uma data válida");
                    continue;
                }
                
                System.out.println("Digite a forma do pagamento: ");
                String formaPagamento = scanner.nextLine();
            
                System.out.println("Digite o tipo do contrato: ");
                String tipo = scanner.nextLine();

                System.out.println("Digite a data da venda (Padrão yyyy-mm-dd): ");

                Date dataVenda = null;
                try{
                    dataVenda = _format.parse(scanner.nextLine());
                }
                catch (ParseException e){
                    System.out.println("O valor inserido não é uma data válida");
                    continue;
                }
                
                System.out.println("Digite o valor da venda: ");
                float valorVenda = scanner.nextFloat();

                // Limpa o Buffer
                scanner.nextLine();

                System.out.println("Digite a data de entrada (Padrão yyyy-mm-dd): ");

                Date dataEntrada = null;
                try{
                    dataEntrada = _format.parse(scanner.nextLine());
                }
                catch (ParseException e){
                    System.out.println("O valor inserido não é uma data válida");
                    continue;
                }
            
                System.out.println("Digite a data da saida (Padrão yyyy-mm-dd): ");

                Date dataSaida = null;
                try{
                    dataSaida = _format.parse(scanner.nextLine());
                }
                catch (ParseException e){
                    System.out.println("O valor inserido não é uma data válida");
                    continue;
                }
                
                System.out.println("Digite o valor da mensalidade: ");
                float valorMensalidade = scanner.nextFloat();

                // Limpa o Buffer
                scanner.nextLine();
            
                    return new Contrato(codContrato, codCliente, codImovel, dataContrato, formaPagamento, tipo, dataVenda, valorVenda, dataEntrada, dataSaida, valorMensalidade);
            }
            catch (Exception e){
                System.out.println("Erro de input" + e.getMessage());
                scanner.nextLine();
                continue;
            }
        }
    }
}