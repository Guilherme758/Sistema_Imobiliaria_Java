package br.edu.univas.sistema_imobiliaria.excelManager;

import br.edu.univas.sistema_imobiliaria.ClienteCorretor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelManagerClienteCorretor {
    public static void exportDataToExcel(ClienteCorretor clienteCorretor) {
        List<ClienteCorretor> clienteCorretorList = List.of(clienteCorretor); // Adiciona o clienteCorretor

        Workbook workbook = null;
        Sheet sheet = null;
        String filePath = "imobiliaria.xlsx"; // Altera o caminho do arquivo

        try {
            // Verifica se o arquivo Excel já existe
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet("ClienteCorretor");

            // Se não existir, cria a planilha
            if (sheet == null) {
                sheet = workbook.createSheet("ClienteCorretor");
                createHeaderRow(sheet); // Cria o cabeçalho, se necessário
            }

            fileInputStream.close(); // Fechar o stream de leitura
        } catch (IOException e) {
            // Se o arquivo não existir, cria um novo workbook
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("ClienteCorretor");
            createHeaderRow(sheet); // Cria o cabeçalho
        }

        // Adicionar os novos clientes e corretores
        exportClientesCorretores(sheet, clienteCorretorList);

        // Salvar o arquivo
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Arquivo Excel atualizado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fechar o workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para criar o cabeçalho da planilha
    private static void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código Cliente");
        headerRow.createCell(1).setCellValue("Código Corretor");
    }

    // Função para buscar a última linha preenchida
    private static int findLastRow(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        return lastRowNum == 0 && sheet.getRow(0) == null ? 1 : lastRowNum + 1;
    }

    // Função para exportar clientes e corretores para o Excel
    private static void exportClientesCorretores(Sheet sheet, List<ClienteCorretor> clienteCorretorList) {
        int rowNum = findLastRow(sheet); // Buscar a próxima linha disponível

        for (ClienteCorretor clienteCorretor : clienteCorretorList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(clienteCorretor.getCodCliente());
            row.createCell(1).setCellValue(clienteCorretor.getCodCorretor());
        }
    }

    // Função para ler clienteCorretor por código ou todos
    public static void readClienteCorretor(int option, int cod) {
        String filePath = "cliente_corretor.xlsx";

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("ClienteCorretor");

            if (sheet == null) {
                System.out.println("Planilha não encontrada.");
                return;
            }

            for (Row row : sheet) {
                int codCliente = (int) row.getCell(0).getNumericCellValue();
                int codCorretor = (int) row.getCell(1).getNumericCellValue();

                if (option == 1 && codCliente == cod) {
                    System.out.printf("Código Cliente: %d | Código Corretor: %d%n", codCliente, codCorretor);
                    return;
                } else if (option == 2) {
                    System.out.printf("Código Cliente: %d | Código Corretor: %d%n", codCliente, codCorretor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para atualizar clienteCorretor por código
    public static void updateClienteCorretor(int cod, ClienteCorretor newClienteCorretor) {
        String filePath = "cliente_corretor.xlsx";

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("ClienteCorretor");

            if (sheet == null) {
                System.out.println("Planilha não encontrada.");
                return;
            }

            for (Row row : sheet) {
                int codCliente = (int) row.getCell(0).getNumericCellValue();

                if (codCliente == cod) {
                    row.getCell(1).setCellValue(newClienteCorretor.getCodCorretor());
                    break;
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Dados atualizados com sucesso!");
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para deletar clienteCorretor por código ou deletar todos
    public static void deleteClienteCorretor(int option, int cod) {
        String filePath = "cliente_corretor.xlsx";

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("ClienteCorretor");

            if (sheet == null) {
                System.out.println("Planilha não encontrada.");
                return;
            }

            if (option == 1) { // Deletar por código
                for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    int codCliente = (int) row.getCell(0).getNumericCellValue();
                    if (codCliente == cod) {
                        sheet.removeRow(row);
                        break;
                    }
                }
            } else if (option == 2) { // Deletar todos
                int lastRow = sheet.getLastRowNum();
                for (int i = 0; i <= lastRow; i++) {
                    sheet.removeRow(sheet.getRow(i));
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Dados deletados com sucesso!");
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}