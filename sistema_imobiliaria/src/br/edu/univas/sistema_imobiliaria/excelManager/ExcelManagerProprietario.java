package br.edu.univas.sistema_imobiliaria.excelManager;

import br.edu.univas.sistema_imobiliaria.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class ExcelManagerProprietario {
    static SimpleDateFormat _format = new SimpleDateFormat("dd/MM/yyyy");

    public static void insertProprietario(Proprietario proprietario){
        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;

        try {
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
            
            try {
                workbook = new XSSFWorkbook(excelFile);
            } catch (Exception e) {
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            sheet = workbook.getSheet("Proprietario");

            if (sheet == null) {
                System.out.println("Aba 'Proprietario' não encontrada, criando nova aba.");
                sheet = workbook.createSheet("Proprietario");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, criando arquivo do zero.");
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Proprietario");
        }

        if (workbook != null && sheet != null) {
            int rowNum = sheet.getLastRowNum();

            if (rowNum == -1) {
                System.out.println("Arquivo sem dados, inserindo cabeçalho e novo proprietário");
                _genericInsert(proprietario, 1, 0, workbook, sheet, null);
            } else {
                System.out.println("Adicionando novos dados ao arquivo");
                _genericInsert(proprietario, 0, rowNum + 1, workbook, sheet, null);
            }
        }
    }

    private static void _genericInsert(Proprietario proprietario, int type, int rowNum, XSSFWorkbook workbook, XSSFSheet sheet, List<Row> rowsToInsert) {
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[] {"cod", "nome", "tipo", "telefone", "cpf", "cnpj"});

        if (type == 1) {
            data.put("2", new Object[] {
                    proprietario.getCod(), proprietario.getNome(),
                    proprietario.getTipo(), proprietario.getTelefone(),
                    proprietario.getCpf(), proprietario.getCnpj()});
        } else if (type == 0) {
            data = new TreeMap<>();

            data.put(String.valueOf(rowNum), new Object[] {
                    proprietario.getCod(), proprietario.getNome(),
                    proprietario.getTipo(), proprietario.getTelefone(),
                    proprietario.getCpf(), proprietario.getCnpj()});
        }

        else if (type == 2) {
            int cont = 2;

            for (Row row : rowsToInsert) {
                data.put(String.valueOf(cont), new Object[] {row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(),
                        row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue()});
                cont++;
            }
        }

        Set<String> keyset = data.keySet();

        for(String key : keyset){
            Row row = sheet.createRow(rowNum);
            rowNum++;

            Object[] arrayElementos = data.get(key);

            int cellNum = 0;

            for(Object elemento : arrayElementos){
                Cell cell = row.createCell(cellNum);
                cellNum++;

                if (elemento instanceof Date){
                    cell.setCellValue(_format.format((Date)elemento));
                } else {
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

    private static void _genericRead(Row row) {
        System.out.print(row.getCell(0) + " | ");
        System.out.print(row.getCell(1) + " | ");
        System.out.print(row.getCell(2) + " | ");
        System.out.print(row.getCell(3) + " | ");
        System.out.print(row.getCell(4) + " | ");
        System.out.print(row.getCell(5) + "\n");
    }

    public static void readProprietario(int cod) {
        try {
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");

            XSSFWorkbook workbook = null;

            try {
                workbook = new XSSFWorkbook(excelFile);
            } catch (Exception e) {
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Proprietario");

            if (sheet == null) {
                System.out.println("Aba 'Proprietario' não encontrada.");
                return;
            }

            Iterator<Row> rows = sheet.rowIterator();

            System.out.println("Imprimindo os dados");

            int cont = 0;

            while (rows.hasNext()) {
                Row row = rows.next();

                if (cont == 0) {
                    _genericRead(row);
                } else {
                    int codProprietario = Integer.parseInt(row.getCell(0).getStringCellValue());

                    if (codProprietario == cod || cod == -1) {
                        _genericRead(row);
                    }
                }
                cont++;
            }
            try {
                workbook.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar o workbook: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não existente");
        }
    }

    public static void deleteProprietario(int cod) {
        try {
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");

            XSSFWorkbook workbook = null;

            try {
                workbook = new XSSFWorkbook(excelFile);
            } catch (Exception e) {
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            if (workbook != null) {
                XSSFSheet sheet = workbook.getSheet("Proprietario");

                if (sheet == null) {
                    System.out.println("Aba 'Proprietario' não encontrada.");
                    return;
                }

                if (cod == -1) {
                    workbook.removeSheetAt(workbook.getSheetIndex("Proprietario"));
                    sheet = workbook.createSheet("Proprietario");
                    _genericInsert(null, 3, 0, workbook, sheet, null);
                    return;
                }

                Iterator<Row> rows = sheet.rowIterator();

                List<Row> rowsToInsert = new ArrayList<>();

                int cont = 0;

                while (rows.hasNext()) {
                    if (cont == 0) {rows.next(); cont++; continue;}

                    Row row = rows.next();
                    int rowCod = Integer.parseInt(row.getCell(0).getStringCellValue());

                    if(rowCod != cod) {
                        rowsToInsert.add(row);
                    }
                }

                workbook.removeSheetAt(workbook.getSheetIndex("Proprietario"));

                sheet = workbook.createSheet("Proprietario");

                _genericInsert(null, 2, 0, workbook, sheet, rowsToInsert);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não existente");
        }
    }

    public static void updateProprietario(Proprietario proprietario, int cod) {
        try {
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");

            XSSFWorkbook workbook = null;

            try {
                workbook = new XSSFWorkbook(excelFile);
            } catch (Exception e) {
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            if (workbook != null) {
                XSSFSheet sheet = workbook.getSheet("Proprietario");

                if (sheet == null) {
                    System.out.println("Aba 'Proprietario' não encontrada.");
                    return;
                }

                Iterator<Row> rows = sheet.rowIterator();

                List<Row> rowsToUpdate = new ArrayList<>();

                int cont = 0;

                while (rows.hasNext()) {
                    if (cont == 0) {rows.next(); cont++; continue;}

                    Row row = rows.next();
                    int rowCod = Integer.parseInt(row.getCell(0).getStringCellValue());

                    if (rowCod == cod) {
                        rowsToUpdate.add(row);
                    }
                }

                for (Row row : rowsToUpdate) {
                    row.getCell(1).setCellValue(proprietario.getCod());
                    row.getCell(2).setCellValue(proprietario.getNome());
                    row.getCell(3).setCellValue(_format.format(proprietario.getTelefone()));
                    row.getCell(4).setCellValue(proprietario.getTipo());
                    row.getCell(5).setCellValue(proprietario.getCpf());
                    row.getCell(6).setCellValue(_format.format(proprietario.getCnpj()));
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
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não existente");
        }
    }
}