package br.edu.univas.sistema_imobiliaria.excelManager;

import br.edu.univas.sistema_imobiliaria.Imovel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelManagerImovel {

public static void insertImovel(Imovel imovel) {
try {
InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
XSSFSheet sheet = workbook.getSheet("Imovel");

int rowNum = sheet.getLastRowNum() + 1;
Row row = sheet.createRow(rowNum);

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

FileOutputStream out = new FileOutputStream("imobiliaria.xlsx");
workbook.write(out);
out.close();
workbook.close();
} catch (IOException e) {
e.printStackTrace();
}
}

public static void readImovel(int cod) {
try {
InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
XSSFSheet sheet = workbook.getSheet("Imovel");

Iterator<Row> rows = sheet.rowIterator();
while (rows.hasNext()) {
Row row = rows.next();
if (cod == -1 || (int) row.getCell(0).getNumericCellValue() == cod) {
for (Cell cell : row) {
System.out.print(cell + " | ");
}
System.out.println();
}
}
workbook.close();
} catch (IOException e) {
e.printStackTrace();
}
}

public static void deleteImovel(int cod) {
try {
InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
XSSFSheet sheet = workbook.getSheet("Imovel");

List<Row> rowsToKeep = new ArrayList<>();
for (Row row : sheet) {
if ((int) row.getCell(0).getNumericCellValue() != cod) {
rowsToKeep.add(row);
}
}

workbook.removeSheetAt(workbook.getSheetIndex("Imovel"));
XSSFSheet newSheet = workbook.createSheet("Imovel");

for (int i = 0; i < rowsToKeep.size(); i++) {
Row newRow = newSheet.createRow(i);
for (int j = 0; j < rowsToKeep.get(i).getLastCellNum(); j++) {
newRow.createCell(j).setCellValue(rowsToKeep.get(i).getCell(j).toString());
}
}

FileOutputStream out = new FileOutputStream("imobiliaria.xlsx");
workbook.write(out);
out.close();
workbook.close();
} catch (IOException e) {
e.printStackTrace();
}
}

public static void updateImovel(Imovel imovel, int cod) {
try {
InputStream excelFile = new FileInputStream("imobiliaria.xlsx");
XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
XSSFSheet sheet = workbook.getSheet("Imovel");

for (Row row : sheet) {
if ((int) row.getCell(0).getNumericCellValue() == cod) {
row.getCell(1).setCellValue(imovel.getCodProprietario());
row.getCell(2).setCellValue(imovel.getCodCorretor());
row.getCell(3).setCellValue(imovel.getRua());
row.getCell(4).setCellValue(imovel.getBairro());
row.getCell(5).setCellValue(imovel.getNumero());
row.getCell(6).setCellValue(imovel.getComplemento());
row.getCell(7).setCellValue(imovel.getCep());
row.getCell(8).setCellValue(imovel.getCidade());
row.getCell(9).setCellValue(imovel.getEstado());
row.getCell(10).setCellValue(imovel.getTipo());
row.getCell(11).setCellValue(imovel.getMetrosQuadrados());
}
}

FileOutputStream out = new FileOutputStream("imobiliaria.xlsx");
workbook.write(out);
out.close();
workbook.close();
} catch (IOException e) {
e.printStackTrace();
}
}
}