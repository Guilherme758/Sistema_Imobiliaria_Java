package br.edu.univas.sistema_imobiliaria;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportExcel {

    public static void exportDataToExcel() {
        // Criar listas de objetos para cada classe
        List<Cliente> clientes = new ArrayList<>();
        List<Corretor> corretores = new ArrayList<>();
        List<Proprietario> proprietarios = new ArrayList<>();
        List<Imovel> imoveis = new ArrayList<>();

        // Adicionar alguns dados de exemplo
        clientes.add(new Cliente(1, "Eduardo Abraão", 31987654321L, "Pessoa Física", "123.456.789-10", ""));
        corretores.add(new Corretor(1, "111.222.333-44", "Corretor 1", 31912345678L));
        proprietarios.add(new Proprietario(1, "Proprietário 1", 31998765432L, "Pessoa Física", "123.456.789-10", ""));
        imoveis.add(new Imovel(1, 1, 1, "Rua A", "Centro", "123", "Apto 1", "12345-678", "São Paulo", "SP", "Apartamento", 70));
        clientes.add(new Cliente(2, "guizao", 87439534859L, "Pessoa Física", "123.345.546-42",""));

        // Criar a planilha no Excel
        Workbook workbook = new XSSFWorkbook();

        // Exportar dados
        exportClientes(workbook, clientes);
        exportCorretores(workbook, corretores);
        exportProprietarios(workbook, proprietarios);
        exportImoveis(workbook, imoveis);

        // Salvar a planilha
        try (FileOutputStream fileOut = new FileOutputStream("imobiliaria.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Arquivo Excel criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fecha o workbook
//        try {
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void exportClientes(Workbook workbook, List<Cliente> clientes) {
        Sheet sheet = workbook.createSheet("Clientes");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código");
        headerRow.createCell(1).setCellValue("Nome");
        headerRow.createCell(2).setCellValue("Telefone");
        headerRow.createCell(3).setCellValue("Tipo");
        headerRow.createCell(4).setCellValue("CPF");
        headerRow.createCell(5).setCellValue("CNPJ");

        int rowNum = 1;
        for (Cliente cliente : clientes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cliente.getCod());
            row.createCell(1).setCellValue(cliente.getNome());
            row.createCell(2).setCellValue(cliente.getTelefone());
            row.createCell(3).setCellValue(cliente.getTipo());
            row.createCell(4).setCellValue(cliente.getCpf());
            row.createCell(5).setCellValue(cliente.getCnpj());
        }
    }

    private static void exportCorretores(Workbook workbook, List<Corretor> corretores) {
        Sheet sheet = workbook.createSheet("Corretores");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código");
        headerRow.createCell(1).setCellValue("CPF");
        headerRow.createCell(2).setCellValue("Nome");
        headerRow.createCell(3).setCellValue("Telefone");

        int rowNum = 1;
        for (Corretor corretor : corretores) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(corretor.getCod());
            row.createCell(1).setCellValue(corretor.getCpf());
            row.createCell(2).setCellValue(corretor.getNome());
            row.createCell(3).setCellValue(corretor.getTelefone());
        }
    }

    private static void exportProprietarios(Workbook workbook, List<Proprietario> proprietarios) {
        Sheet sheet = workbook.createSheet("Proprietários");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código");
        headerRow.createCell(1).setCellValue("Nome");
        headerRow.createCell(2).setCellValue("Telefone");
        headerRow.createCell(3).setCellValue("Tipo");
        headerRow.createCell(4).setCellValue("CPF");
        headerRow.createCell(5).setCellValue("CNPJ");

        int rowNum = 1;
        for (Proprietario proprietario : proprietarios) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(proprietario.getCod());
            row.createCell(1).setCellValue(proprietario.getNome());
            row.createCell(2).setCellValue(proprietario.getTelefone());
            row.createCell(3).setCellValue(proprietario.getTipo());
            row.createCell(4).setCellValue(proprietario.getCpf());
            row.createCell(5).setCellValue(proprietario.getCnpj());
        }
    }

    private static void exportImoveis(Workbook workbook, List<Imovel> imoveis) {
        Sheet sheet = workbook.createSheet("Imóveis");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código");
        headerRow.createCell(1).setCellValue("Código Proprietário");
        headerRow.createCell(2).setCellValue("Código Corretor");
        headerRow.createCell(3).setCellValue("Rua");
        headerRow.createCell(4).setCellValue("Bairro");
        headerRow.createCell(5).setCellValue("Número");
        headerRow.createCell(6).setCellValue("Complemento");
        headerRow.createCell(7).setCellValue("CEP");
        headerRow.createCell(8).setCellValue("Cidade");
        headerRow.createCell(9).setCellValue("Estado");
        headerRow.createCell(10).setCellValue("Tipo");
        headerRow.createCell(11).setCellValue("Metros Quadrados");

        int rowNum = 1;
        for (Imovel imovel : imoveis) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(imovel.getCod());
            row.createCell(1).setCellValue(imovel.getCodProprietario());
            row.createCell(2).setCellValue(imovel.getCodCorretor());
            row.createCell(3).setCellValue(imovel.getRua());
            row.createCell(4).setCellValue(imovel.getBairro());
            row.createCell(5).setCellValue(imovel.getNumero());
            row.createCell(6).setCellValue(imovel.getComplemento());
            row.createCell(7).setCellValue(imovel.getCep());
            row.createCell(8).setCellValue(imovel.getCidade());
            row.createCell(9).setCellValue(imovel.getEstado());
            row.createCell(10).setCellValue(imovel.getTipo());
            row.createCell(11).setCellValue(imovel.getMetrosQuadrados());
        }
    }
}
