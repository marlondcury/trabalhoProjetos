
package model;


public class Produto {
    
    private int id;
    private String nome;
    private Double precoCusto;
    private Categoria categoria;
    
    private Double precoVendaAtual;
    private Double margemLucroAtual;
    
    
    public Produto(String nome, Double precoCusto, Categoria categoria ){
        
       
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.categoria = categoria;
        
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }
    
    
    public Categoria getCategoria() {
        return categoria;
    }

    public Double getMargemLucroAtual() {
        return margemLucroAtual;
    }

    public Double getPrecoVendaAtual() {
        return precoVendaAtual;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    

   public void atualizarPrecoMargemAtual(Double precoVendaAtual, Double margemLucroAtual){
       this.precoVendaAtual = precoVendaAtual;
       this.margemLucroAtual = margemLucroAtual;
   }
    
    
    
     
}
