
package model;


public class Cliente {
    
    private int id;
    private String nome;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private TipoCliente tipoCliente;
    private Double totalCompras;

    public Cliente( String nomeCliente, String logradouro, String bairro, String cidade, String uf ) {
        this.nome = nomeCliente;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.tipoCliente = TipoCliente.PRATA;
        this.totalCompras = 0.0;
    }
    
    public void registrarCompras(Double valor){
        this.totalCompras += valor;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public int getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNomeCliente() {
        return nome;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public Double getTotalCompras() {
        return totalCompras;
    }

    public String getUf() {
        return uf;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNomeCliente(String nome) {
        this.nome = nome;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return nome;
                
     }

    
}
