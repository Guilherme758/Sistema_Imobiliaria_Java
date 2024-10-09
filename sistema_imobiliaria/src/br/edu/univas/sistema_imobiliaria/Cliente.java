package br.edu.univas.sistema_imobiliaria;

public class Cliente {
    private int cod;
    private String nomeCliente;
    private Long numTelefone;
    private String tipoCliente;
    private String numCPF;
    private String numCNPJ;

    // Construtor
    public Cliente(int cod, String nomeCliente, long numTelefone, String tipoCliente, String numCPF, String numCNPJ) {
        this.cod = cod;
        this.nomeCliente = nomeCliente;
        this.numTelefone = numTelefone;
        this.tipoCliente = tipoCliente;
        this.numCPF = numCPF;
        this.numCNPJ = numCNPJ;
    }

    // Getters
    public int getCod() {
        return cod;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Long getNumTelefone() {
        return numTelefone;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getNumCPF() {
        return numCPF;
    }

    public String getNumCNPJ() {
        return numCNPJ;
    }
}
