package br.edu.univas.sistema_imobiliaria.excelManager;

import br.edu.univas.sistema_imobiliaria.Cliente;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class ExcelManagerCliente {

    public static void insertCliente(Cliente cliente) {
        try {
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
            XSSFWorkbook workbook = null;

            try {
                workbook = new XSSFWorkbook(excelFile);
            } catch (Exception e) {
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Cliente");
            if (sheet == null) {
                System.out.println("Criando a planilha 'Cliente'.");
                sheet = workbook.createSheet("Cliente");
            }

            int rowNum = sheet.getLastRowNum();

            if (rowNum == -1) {
                System.out.println("Arquivo sem dados, inserindo cabeçalho e novo cliente");
                _genericInsert(cliente, 1, 0, workbook, sheet);
            } else {
                System.out.println("Adicionando novos dados ao arquivo");
                _genericInsert(cliente, 0, rowNum + 1, workbook, sheet);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Criando arquivo do zero");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Cliente");
            _genericInsert(cliente, 1, 0, workbook, sheet);
        }
    }

    private static void _genericInsert(Cliente cliente, int type, int rowNum, XSSFWorkbook workbook, XSSFSheet sheet) {
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"cod", "nome_cliente", "num_telefone", "tipo_cliente", "num_cpf", "num_cnpj"});

        if (type == 1 || type == 0) {
            data.put(String.valueOf(type == 1 ? 2 : rowNum), new Object[]{
                    cliente.getCod(), cliente.getNomeCliente(), cliente.getNumTelefone(),
                    cliente.getTipoCliente(), cliente.getNumCPF(), cliente.getNumCNPJ()
            });
        }

        Set<String> keyset = data.keySet();

        for (String key : keyset) {
            Row row = sheet.createRow(rowNum);
            rowNum++;
            Object[] arrayElementos = data.get(key);
            int cellNum = 0;

            for (Object elemento : arrayElementos) {
                Cell cell = row.createCell(cellNum);
                cellNum++;

                if (elemento != null) {
                    cell.setCellValue(elemento.toString());
                }
            }
        }

        try {
            FileOutputStream out = new FileOutputStream("imobiliaria.xlsx");
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (Exception e) {
            System.out.println("Erro para salvar o arquivo: " + e.getMessage());
        }
    }

    public static void readCliente(int cod) {
        try {
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
            XSSFWorkbook workbook = null;

            try {
                workbook = new XSSFWorkbook(excelFile);
            } catch (Exception e) {
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Cliente");
            Iterator<Row> rows = sheet.rowIterator();
            System.out.println("Imprimindo os dados");
            int cont = 0;

            while (rows.hasNext()) {
                Row row = rows.next();

                // Printa o cabeçalho
                if (cont == 0) {
                    _genericRead(row);
                } else {
                    int codCliente = Integer.parseInt(row.getCell(0).getStringCellValue());

                    if (codCliente == cod || cod == -1) {
                        _genericRead(row);
                    }
                }
                cont++;
            }

            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não existente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void _genericRead(Row row) {
        System.out.print(row.getCell(0) + " | ");
        System.out.print(row.getCell(1) + " | ");
        System.out.print(row.getCell(2) + " | ");
        System.out.print(row.getCell(3) + " | ");
        System.out.print(row.getCell(4) + " | ");
        System.out.print(row.getCell(5) + "\n");
    }

    public static void deleteCliente(int cod) {
        try {
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            XSSFSheet sheet = workbook.getSheet("Cliente");

            if (cod == -1) {
                workbook.removeSheetAt(workbook.getSheetIndex("Cliente"));
                sheet = workbook.createSheet("Cliente");
                _genericInsert(null, 3, 0, workbook, sheet);
                return;
            }

            Iterator<Row> rows = sheet.rowIterator();
            List<Row> rowsToInsert = new ArrayList<>();

            int cont = 0;

            while (rows.hasNext()) {
                Row row = rows.next();
                // Ignora a primeira linha (cabeçalho)
                if (cont == 0) {
                    cont++;
                    continue;
                }

                int rowCod;
                // Tente capturar a exceção ao converter, para evitar crashes
                try {
                    rowCod = Integer.parseInt(row.getCell(0).getStringCellValue());
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter código do cliente: " + e.getMessage());
                    continue; // Pula para a próxima linha
                }

                if (rowCod != cod) {
                    rowsToInsert.add(row);
                }
            }

            workbook.removeSheetAt(workbook.getSheetIndex("Cliente"));
            sheet = workbook.createSheet("Cliente");
            _genericInsert(null, 2, 0, workbook, sheet, rowsToInsert);
        } catch (IOException e) {
            System.out.println("Arquivo não existente");
        }
    }

    public static void updateCliente(Cliente cliente, int cod) {
        try (InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
             XSSFWorkbook workbook = new XSSFWorkbook(excelFile)) {

            XSSFSheet sheet = workbook.getSheet("Cliente");
            Iterator<Row> rows = sheet.rowIterator();
            boolean updated = false;

            // Ignora a primeira linha (cabeçalho)
            if (rows.hasNext()) {
                rows.next(); // Ignora a linha do cabeçalho
            }

            while (rows.hasNext()) {
                Row row = rows.next();
                int rowCod;

                // Verifica se a célula do código do cliente é do tipo numérico
                try {
                    if (row.getCell(0).getCellType() == CellType.NUMERIC) {
                        rowCod = (int) row.getCell(0).getNumericCellValue();
                    } else {
                        rowCod = Integer.parseInt(row.getCell(0).getStringCellValue());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter código do cliente: " + e.getMessage());
                    continue; // Pula para a próxima linha
                }

                if (rowCod == cod) {
                    row.getCell(1).setCellValue(cliente.getNomeCliente());
                    row.getCell(2).setCellValue(cliente.getNumTelefone());
                    row.getCell(3).setCellValue(cliente.getTipoCliente());
                    row.getCell(4).setCellValue(cliente.getNumCPF());
                    row.getCell(5).setCellValue(cliente.getNumCNPJ());
                    updated = true; // Marca que houve uma atualização
                }
            }

            if (updated) {
                try (FileOutputStream out = new FileOutputStream("imobiliaria.xlsx")) {
                    workbook.write(out);
                    System.out.println("Dados atualizados com sucesso!");
                }
            } else {
                System.out.println("Código do cliente não encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Arquivo não existente: " + e.getMessage());
        }
    }

    private static void _genericInsert(Cliente cliente, int type, int rowNum, XSSFWorkbook workbook, XSSFSheet sheet, List<Row> rowsToInsert) {
        if (type == 2) {
            // Reinsere as linhas do List<Row>
            for (Row row : rowsToInsert) {
                Row newRow = sheet.createRow(rowNum++);
                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                    Cell cell = newRow.createCell(i);
                    cell.setCellValue(row.getCell(i).getStringCellValue());
                }
            }
        }
    }
}