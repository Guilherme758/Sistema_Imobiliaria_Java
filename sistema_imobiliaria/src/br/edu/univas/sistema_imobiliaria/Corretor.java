package br.edu.univas.sistema_imobiliaria;

public class Corretor {
    private int cod;
    private String cpf;
    private String nome;
    private long telefone;

    public Corretor(int cod, String cpf, String nome, long telefone){
        this.cod = cod;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getCod(){
        return this.cod;
    }

    public void setCod(int cod){
        this.cod = cod;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
    public long getTelefone(){
        return this.telefone;
    }

    public void setTelefone(long telefone){
        this.telefone = telefone;
    }
}
