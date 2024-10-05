package br.edu.univas.sistema_imobiliaria.excelManager;

import br.edu.univas.sistema_imobiliaria.*;
import br.edu.univas.sistema_imobiliaria.userInputHandler.UserInputHandlerContrato;

import java.util.*;

import org.apache.poi.xssf.usermodel.*;

public class ExcelManagerContrato {
    List<String> _contratoColumns = List.of("cod", "cod_cliente", "cod_imovel", "data_contrato", "forma_pagamento", "tipo", "data_venda", "valor_venda", "data_entrada", "data_saida", "valor_mensalidade");
    
    public void insertContrato(){
        Contrato contrato = UserInputHandlerContrato.userInputHandlerContrato();
        
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Contrato");

        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {_contratoColumns});
        data.put("2", new Object[] {contrato.getCod(), contrato.getCodCliente(), contrato.getCodImovel(),
                                   contrato.getDataContrato(), contrato.getFormaPagamento(),
                                   contrato.getTipo(), contrato.getDataVenda(), contrato.getValorVenda(),
                                   contrato.getDataEntrada(), contrato.getDataSaida(), contrato.getValorMensalidade()});

        Set<String> keyset = data.keySet();

        
    }
}
