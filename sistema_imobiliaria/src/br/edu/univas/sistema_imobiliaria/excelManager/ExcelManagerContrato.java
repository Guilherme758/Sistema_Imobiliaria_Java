package br.edu.univas.sistema_imobiliaria.excelManager;

import br.edu.univas.sistema_imobiliaria.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class ExcelManagerContrato {    
    static SimpleDateFormat _format = new SimpleDateFormat("dd/MM/yyyy");

    public static void insertContrato(Contrato contrato){
        try{
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
        
            XSSFWorkbook workbook = null;
            
            try{
                workbook = new XSSFWorkbook(excelFile);
            }
            catch (Exception e){
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Contrato");

            int rowNum = sheet.getLastRowNum();

            if(rowNum == -1){
                System.out.println("Arquivo sem dados, inserindo cabeçalho e novo contrato");
                _genericInsert(contrato, 1, 0, workbook, sheet, null);
            }
            else{
                System.out.println("Adicionando novos dados ao arquivo");
                _genericInsert(contrato, 0, rowNum + 1, workbook, sheet, null);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Criando arquivo do zero");

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("Contrato");

            _genericInsert(contrato, 1, 0, workbook, sheet, null);
        }
    }

    private static void _genericInsert(Contrato contrato, int type, int rowNum, XSSFWorkbook workbook, XSSFSheet sheet, List<Row> rowsToInsert){
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"cod", "cod_cliente", "cod_imovel", "data_contrato", "forma_pagamento", "tipo", 
                                        "data_venda", "valor_venda", "data_entrada", "data_saida", "valor_mensalidade"});
        
        if (type == 1){
            data.put("2", new Object[] {contrato.getCod(), contrato.getCodCliente(), contrato.getCodImovel(),
                                    contrato.getDataContrato(), contrato.getFormaPagamento(),
                                    contrato.getTipo(), contrato.getDataVenda(), contrato.getValorVenda(),
                                    contrato.getDataEntrada(), contrato.getDataSaida(), contrato.getValorMensalidade()});
        }
        else if (type == 0){
            data = new TreeMap<String, Object[]>();

            data.put(String.valueOf(rowNum), new Object[] {contrato.getCod(), contrato.getCodCliente(), contrato.getCodImovel(),
                contrato.getDataContrato(), contrato.getFormaPagamento(),
                contrato.getTipo(), contrato.getDataVenda(), contrato.getValorVenda(),
                contrato.getDataEntrada(), contrato.getDataSaida(), contrato.getValorMensalidade()});                          
        }

        else if (type == 2){
            int cont = 2; 

            for (Row row : rowsToInsert){
                data.put(String.valueOf(cont), new Object[] {row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
                                                             row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(), 
                                                             row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue(), 
                                                             row.getCell(6).getStringCellValue(), row.getCell(7).getStringCellValue(), 
                                                             row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue(), 
                                                             row.getCell(10).getStringCellValue()});
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

    public static void readContrato(){
        try{
            InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
        
            XSSFWorkbook workbook = null;
            
            try{
                workbook = new XSSFWorkbook(excelFile);
            }
            catch (Exception e){
                System.out.println("Arquivo não existente ou inválido: " + e.getMessage());
            }

            XSSFSheet sheet = workbook.getSheet("Contrato");

            Iterator<Row> rows = sheet.rowIterator();

            System.out.println("Imprimindo os dados");

            while (rows.hasNext()){
                Row row = rows.next();

                System.out.print(row.getCell(0) + " | ");
                System.out.print(row.getCell(1) + " | ");
                System.out.print(row.getCell(2) + " | ");
                System.out.print(row.getCell(3) + " | ");
                System.out.print(row.getCell(4) + " | ");
                System.out.print(row.getCell(5) + " | ");
                System.out.print(row.getCell(6) + " | ");
                System.out.print(row.getCell(7) + " | ");
                System.out.print(row.getCell(8) + " | ");
                System.out.print(row.getCell(9) + " | ");
                System.out.print(row.getCell(10) + "\n");
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

    public static void deleteContrato(int cod){
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
                workbook.removeSheetAt(workbook.getSheetIndex("Contrato"));

                XSSFSheet sheet = workbook.createSheet("Contrato");

                _genericInsert(null, 3, 0, workbook, sheet, null);

                return;
            }

            XSSFSheet sheet = workbook.getSheet("Contrato");

            Iterator<Row> rows = sheet.rowIterator();

            List<Row> rowsToInsert = new ArrayList<Row>();

            int cont = 0;

            while (rows.hasNext()){
                if (cont == 0) {rows.next(); cont++; continue;}

                Row row = rows.next();
                int rowCod = Integer.parseInt(row.getCell(0).getStringCellValue());

                if(rowCod != cod){
                    rowsToInsert.add(row);
                }
            }

            workbook.removeSheetAt(workbook.getSheetIndex("Contrato"));

            sheet = workbook.createSheet("Contrato");

            _genericInsert(null, 2, 0, workbook, sheet, rowsToInsert);
        }
        catch (FileNotFoundException e){
            System.out.println("Arquivo não existente");
        }
    }
}