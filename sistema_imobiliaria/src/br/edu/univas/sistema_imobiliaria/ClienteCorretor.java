package br.edu.univas.sistema_imobiliaria;

public class ClienteCorretor {
    private int codCliente;
    private int codCorretor;

    public ClienteCorretor(int codCliente, int codCorretor) {
        this.codCliente = codCliente;
        this.codCorretor = codCorretor;
    }

    // Getters e Setters
    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodCorretor() {
        return codCorretor;
    }

    public void setCodCorretor(int codCorretor) {
        this.codCorretor = codCorretor;
    }

    //public void exibirRelacao() {
    //    System.out.println("Código do Cliente: " + codCliente);
    //    System.out.println("Código do Corretor: " + codCorretor);
    //}
}