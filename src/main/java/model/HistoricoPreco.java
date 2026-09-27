
package model;

import java.time.LocalDate;


public class HistoricoPreco {
    
    private final LocalDate data;
    private final Double percentualLucro;
    private final Double precoVenda;
    private final Produto produto;
    
    public HistoricoPreco(LocalDate data, Double percentualLucro,Double precoVenda, Produto produto){
        this.percentualLucro = percentualLucro;
        this.precoVenda = precoVenda;
        this.data = data;
        this.produto = produto;
    }

    public LocalDate getData() {
        return data;
    }

    public Double getPercentualLucro() {
        return percentualLucro;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public Produto getProduto() {
        return produto;
    }
    
}
