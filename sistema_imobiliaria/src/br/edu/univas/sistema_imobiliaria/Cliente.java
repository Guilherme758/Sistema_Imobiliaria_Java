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

    //getter setters
    public int getCod() {
        return cod;
    }
    public void setCod(int cod) {
        this.cod = cod;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public long getTelefone() {
        return telefone;
    }
    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
