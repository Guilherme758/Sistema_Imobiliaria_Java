package br.edu.univas.sistema_imobiliaria;

public class Imovel {
    private int cod;
    private int codProprietario;
    private int codCorretor;
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String tipo;
    private int metrosQuadrados;

    // Constructor
    public Imovel(int cod, int codProprietario, int codCorretor, String rua, String bairro, String numero,
                  String complemento, String cep, String cidade, String estado, String tipo, int metrosQuadrados) {
        this.cod = cod;
        this.codProprietario = codProprietario;
        this.codCorretor = codCorretor;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.tipo = tipo;
        this.metrosQuadrados = metrosQuadrados;
    }

    // Getters and setters
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCodProprietario() {
        return codProprietario;
    }

    public void setCodProprietario(int codProprietario) {
        this.codProprietario = codProprietario;
    }

    public int getCodCorretor() {
        return codCorretor;
    }

    public void setCodCorretor(int codCorretor) {
        this.codCorretor = codCorretor;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMetrosQuadrados() {
        return metrosQuadrados;
    }

    public void setMetrosQuadrados(int metrosQuadrados) {
        this.metrosQuadrados = metrosQuadrados;
    }
}

