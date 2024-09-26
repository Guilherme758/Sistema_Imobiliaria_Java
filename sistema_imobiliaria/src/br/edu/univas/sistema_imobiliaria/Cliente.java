package br.edu.univas.sistema_imobiliaria;

public class Cliente {
    private int cod;
    private String nome;
    private long telefone;
    private String tipo;
    private String cpf;
    private String cnpj;

    public Cliente(int cod, String nome, long telefone, String tipo, String cpf, String cnpj) {
        this.cod = cod;
        this.nome = nome;
        this.telefone = telefone;
        this.tipo = tipo;
        this.cpf = cpf;
        this.cnpj = cnpj;
    }

    
}
