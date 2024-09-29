package br.edu.univas.sistema_imobiliaria;

import java.util.*;

public class Contrato {
    private int cod;
    private int cod_cliente;
    private int cod_imovel;
    private Date data_contrato;
    private String forma_pagamento;
    private String tipo;
    private Date data_venda;
    private float valor_venda;
    private Date data_entrada;
    private Date data_saida;
    private float valor_mensalidade;

    public Contrato(int cod, int cod_cliente, int cod_imovel, Date data_contrato, String forma_pagamento, 
                    String tipo, Date data_venda, float valor_venda, Date data_entrada, 
                    Date data_saida, float valor_mensalidade){            
            this.cod               = cod;  
            this.cod_cliente       = cod_cliente;
            this.cod_imovel        = cod_imovel;
            this.data_contrato     = data_contrato;
            this.forma_pagamento   = forma_pagamento;
            this.tipo              = tipo;
            this.data_venda        = data_venda;
            this.valor_venda       = valor_venda;
            this.data_entrada      = data_entrada;
            this.data_saida        = data_saida;
            this.valor_mensalidade = valor_mensalidade;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCodCliente() {
        return this.cod_cliente;
    }

    public void setCodCliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public int getCodImovel() {
        return this.cod_imovel;
    }

    public void setCodImovel(int cod_imovel) {
        this.cod_imovel = cod_imovel;
    }

    public Date getDataContrato() {
        return this.data_contrato;
    }

    public void setDataContrato(Date data_contrato) {
        this.data_contrato = data_contrato;
    }

    public String getFormaPagamento() {
        return this.forma_pagamento;
    }

    public void setFormaPagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataVenda() {
        return this.data_venda;
    }

    public void setDataVenda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public float getValorVenda() {
        return this.valor_venda;
    }

    public void setValorVenda(float valor_venda) {
        this.valor_venda = valor_venda;
    }

    public Date getDataEntrada() {
        return this.data_entrada;
    }

    public void setDataEntrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Date getDataSaida() {
        return this.data_saida;
    }

    public void setDataSaida(Date data_saida) {
        this.data_saida = data_saida;
    }

    public float getValorMensalidade() {
        return this.valor_mensalidade;
    }

    public void setValorMensalidade(float valor_mensalidade) {
        this.valor_mensalidade = valor_mensalidade;
    }
}
