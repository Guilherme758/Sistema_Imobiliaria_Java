package br.edu.univas.sistema_imobiliaria.excelManager;

import br.edu.univas.sistema_imobiliaria.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class ExcelManagerProprietario {
    static SimpleDateFormat _format = new SimpleDateFormat("dd/MM/yyyy");

    public static void insertProprietario(Proprietario Proprietario){
        try{
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");

            XSSFWorkbook workbook = null;

            try{
                workbook = new XSSFWorkbook(excelFile);
            }
            catch (Exception e){
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Proprietario");

            int rowNum = sheet.getLastRowNum();

            if(rowNum == -1){
                System.out.println("Arquivo sem dados, inserindo cabeçalho e novo Proprietario");
                _genericInsert(Proprietario, 1, 0, workbook, sheet, null);
            }
            else{
                System.out.println("Adicionando novos dados ao arquivo");
                _genericInsert(Proprietario, 0, rowNum + 1, workbook, sheet, null);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Criando arquivo do zero");

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("Proprietario");

            _genericInsert(Proprietario, 1, 0, workbook, sheet, null);
        }
    }

    private static void _genericInsert(Proprietario Proprietario, int type, int rowNum, XSSFWorkbook workbook, XSSFSheet sheet, List<Row> rowsToInsert){
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"cod", "nome", "telefone", "tipo", "cpf", "cnpj"});

        if (type == 1){
            data.put("2", new Object[] {
                    Proprietario.getCod(), Proprietario.getNome(),
                    Proprietario.getTipo(), Proprietario.getTelefone(),
                    Proprietario.getCpf(), Proprietario.getCnpj()});
        }
        else if (type == 0){
            data = new TreeMap<String, Object[]>();

            data.put(String.valueOf(rowNum), new Object[] {
                    Proprietario.getCod(), Proprietario.getNome(),
                    Proprietario.getTipo(), Proprietario.getTelefone(),
                    Proprietario.getCpf(), Proprietario.getCnpj()});
        }

        else if (type == 2){
            int cont = 2;

            for (Row row : rowsToInsert){
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
                }
                else{
                    cell.setCellValue(elemento.toString());
                }
            }
        }

        try{
            FileOutputStream out = new FileOutputStream("imobiliaria.xlsx");
            workbook.write(out);
            out.close();
            workbook.close();
        }
        catch (Exception e){
            System.out.println("Erro para salvar o arquivo: " + e.getMessage());
        }
    }

    private static void _genericRead(Row row){
        System.out.print(row.getCell(0) + " | ");
        System.out.print(row.getCell(1) + " | ");
        System.out.print(row.getCell(2) + " | ");
        System.out.print(row.getCell(3) + " | ");
        System.out.print(row.getCell(4) + " | ");
        System.out.print(row.getCell(5) + "\n");
    }

    public static void readProprietario(int cod){
        try{
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");

            XSSFWorkbook workbook = null;

            try{
                workbook = new XSSFWorkbook(excelFile);
            }
            catch (Exception e){
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Proprietario");

            Iterator<Row> rows = sheet.rowIterator();

            System.out.println("Imprimindo os dados");

            int cont = 0;

            while (rows.hasNext()){
                Row row = rows.next();

                // Printa o cabeçalho
                if (cont == 0){
                    _genericRead(row);
                }
                // Printa apenas as linhas com o código ou printa todas se o cod for -1
                else{
                    int codProprietario = Integer.parseInt(row.getCell(0).getStringCellValue());

                    if (codProprietario == cod || cod == -1){
                        _genericRead(row);
                    }
                }
                cont++;
            }
            try{
                workbook.close();
            }
            catch (Exception e){
                System.out.println("Erro ao fechar o workbook" + e.getMessage());
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Arquivo não existente");
        }
    }

    public static void deleteProprietario(int cod){
        try{
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");

            XSSFWorkbook workbook = null;

            try{
                workbook = new XSSFWorkbook(excelFile);
            }
            catch (Exception e){
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            if (cod == -1){
                workbook.removeSheetAt(workbook.getSheetIndex("Proprietario"));

                XSSFSheet sheet = workbook.createSheet("Proprietario");

                _genericInsert(null, 3, 0, workbook, sheet, null);

                return;
            }

            XSSFSheet sheet = workbook.getSheet("Proprietario");

            Iterator<Row> rows = sheet.rowIterator();

            List<Row> rowsToInsert = new ArrayList<Row>();

            int cont = 0;

            while (rows.hasNext()){
                // Ignora a linha do cabeçalho
                if (cont == 0) {rows.next(); cont++; continue;}

                Row row = rows.next();
                int rowCod = Integer.parseInt(row.getCell(0).getStringCellValue());

                if(rowCod != cod){
                    rowsToInsert.add(row);
                }
            }

            workbook.removeSheetAt(workbook.getSheetIndex("Proprietario"));

            sheet = workbook.createSheet("Proprietario");

            _genericInsert(null, 2, 0, workbook, sheet, rowsToInsert);
        }
        catch (FileNotFoundException e){
            System.out.println("Arquivo não existente");
        }
    }

    public static void updateProprietario(Proprietario Proprietario, int cod){
        try{
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");

            XSSFWorkbook workbook = null;

            try{
                workbook = new XSSFWorkbook(excelFile);
            }
            catch (Exception e){
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Proprietario");

            Iterator<Row> rows = sheet.rowIterator();

            List<Row> rowsToUpdate = new ArrayList<Row>();

            int cont = 0;

            while (rows.hasNext()){
                // Ignora a linha do cabeçalho
                if (cont == 0) {rows.next(); cont++; continue;}

                Row row = rows.next();
                int rowCod = Integer.parseInt(row.getCell(0).getStringCellValue());

                if(rowCod == cod){
                    rowsToUpdate.add(row);
                }
            }

            for (Row row : rowsToUpdate){
                row.getCell(1).setCellValue(Proprietario.getCod());
                row.getCell(2).setCellValue(Proprietario.getNome());
                row.getCell(3).setCellValue(_format.format(Proprietario.getTelefone()));
                row.getCell(4).setCellValue(Proprietario.getTipo());
                row.getCell(5).setCellValue(Proprietario.getCpf());
                row.getCell(6).setCellValue(_format.format(Proprietario.getCnpj()));
            }

            try{
                FileOutputStream out = new FileOutputStream("imobiliaria.xlsx");
                workbook.write(out);
                out.close();
                workbook.close();
            }
            catch (Exception e){
                System.out.println("Erro para salvar o arquivo: " + e.getMessage());
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Arquivo não existente");
        }
    }
}
