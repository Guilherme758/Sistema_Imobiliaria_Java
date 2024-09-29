package br.edu.univas.sistema_imobiliaria;

public class  Main {
    public static void main(String[] args) {
        System.out.println("Exportando os dados");

        //exporta para o Excel
        ExportExcel.exportDataToExcel();
        System.out.println("Exportacao concluida!");
    }
}
