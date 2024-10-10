package br.edu.univas.sistema_imobiliaria.userInputHandler;

import java.util.Scanner;
import br.edu.univas.sistema_imobiliaria.Imovel;

public class UserInputHandlerImovel {

public static Imovel userInputHandlerImovel(Scanner scanner, int operationType, int cod) {
int codProprietario, codCorretor, metrosQuadrados;
String rua, bairro, numero, complemento, cep, cidade, estado, tipo;

if (operationType == 1) { 
System.out.println("Atualizando Imóvel com código: " + cod);
} else { 
System.out.println("Inserindo novo Imóvel");
}

System.out.print("Digite o código do proprietário: ");
codProprietario = scanner.nextInt();
scanner.nextLine(); 

System.out.print("Digite o código do corretor: ");
codCorretor = scanner.nextInt();
scanner.nextLine();

System.out.print("Digite a rua: ");
rua = scanner.nextLine();

System.out.print("Digite o bairro: ");
bairro = scanner.nextLine();

System.out.print("Digite o número: ");
numero = scanner.nextLine();

System.out.print("Digite o complemento: ");
complemento = scanner.nextLine();

System.out.print("Digite o CEP: ");
cep = scanner.nextLine();

System.out.print("Digite a cidade: ");
cidade = scanner.nextLine();

System.out.print("Digite o estado: ");
estado = scanner.nextLine();

System.out.print("Digite o tipo de imóvel: ");
tipo = scanner.nextLine();

System.out.print("Digite a metragem quadrada: ");
metrosQuadrados = scanner.nextInt();
scanner.nextLine(); 

return new Imovel(cod, codProprietario, codCorretor, rua, bairro, numero, complemento, cep, cidade, estado, tipo, metrosQuadrados);
}
}