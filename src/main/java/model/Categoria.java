
package model;

public class Categoria {
    
    private int id;
    private String nome;
    private Double percentualLucro;
    
    public Categoria(String nome, Double percentualLucro ){
        
        this.nome = nome;
        this.percentualLucro = percentualLucro;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPercentualLucro() {
        return percentualLucro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPercentualLucro(Double percentualLucro) {
        this.percentualLucro = percentualLucro;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
       
        return nome;
    }
    
    
}
