package br.edu.univas.sistema_imobiliaria.excelManager;

import br.edu.univas.sistema_imobiliaria.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class ExcelManagerCorretor {    
    static SimpleDateFormat _format = new SimpleDateFormat("dd/MM/yyyy");

    public static void insertCorretor(Corretor corretor){
        try{
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
        
            XSSFWorkbook workbook = null;
            
            try{
                workbook = new XSSFWorkbook(excelFile);
            }
            catch (Exception e){
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Corretor");

            int rowNum = sheet.getLastRowNum();

            if(rowNum == -1){
                System.out.println("Arquivo sem dados, inserindo cabeçalho e novo corretor");
                _genericInsert(corretor, 1, 0, workbook, sheet, null);
            }
            else{
                System.out.println("Adicionando novos dados ao arquivo");
                _genericInsert(corretor, 0, rowNum + 1, workbook, sheet, null);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Criando arquivo do zero");

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("Corretor");

            _genericInsert(corretor, 1, 0, workbook, sheet, null);
        }
    }

    private static void _genericInsert(Corretor corretor, int type, int rowNum, XSSFWorkbook workbook, XSSFSheet sheet, List<Row> rowsToInsert){
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"cod", "cpf", "nome", "telefone"});
        
        if (type == 1){
            data.put("2", new Object[] {corretor.getCod(), corretor.getCpf(), corretor.getNome(), corretor.getTelefone()});
        }
        else if (type == 0){
            data = new TreeMap<String, Object[]>();

            data.put(String.valueOf(rowNum), new Object[] {corretor.getCod(), corretor.getCpf(), corretor.getNome(), corretor.getTelefone()});                          
        }

        else if (type == 2){
            int cont = 2; 

            for (Row row : rowsToInsert){
                data.put(String.valueOf(cont), new Object[] {row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
                                                             row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue()});
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
        System.out.print(row.getCell(3) + " | "+"\n");
    }
    
    public static void readCorretor(int cod){
        try{
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
        
            XSSFWorkbook workbook = null;
            
            try{
                workbook = new XSSFWorkbook(excelFile);
            }
            catch (Exception e){
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Corretor");

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
                    int codCorretor = Integer.parseInt(row.getCell(0).getStringCellValue());

                    if (codCorretor == cod || cod == -1){
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

    public static void deleteCorretor(int cod){
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
                workbook.removeSheetAt(workbook.getSheetIndex("Corretor"));

                XSSFSheet sheet = workbook.createSheet("Corretor");

                _genericInsert(null, 3, 0, workbook, sheet, null);

                return;
            }

            XSSFSheet sheet = workbook.getSheet("Corretor");

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

            workbook.removeSheetAt(workbook.getSheetIndex("Corretor"));

            sheet = workbook.createSheet("Corretor");

            _genericInsert(null, 2, 0, workbook, sheet, rowsToInsert);
        }
        catch (FileNotFoundException e){
            System.out.println("Arquivo não existente");
        }
    }

    public static void updateCorretor(Corretor corretor, int cod){
        try{
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
        
            XSSFWorkbook workbook = null;
            
            try{
                workbook = new XSSFWorkbook(excelFile);
            }
            catch (Exception e){
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Corretor");

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
                row.getCell(1).setCellValue(corretor.getCpf());
                row.getCell(2).setCellValue(corretor.getNome());
                row.getCell(3).setCellValue(corretor.getTelefone());
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