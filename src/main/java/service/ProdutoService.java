
package service;

import java.util.List;
import model.Categoria;
import model.Produto;
import repository.ICategoriaRepository;
import repository.IProdutoRepository;

public class ProdutoService {
    
    private IProdutoRepository produtoRepo;
    private ICategoriaRepository categoriaRepo;
    
    public ProdutoService(IProdutoRepository produtoRepo,ICategoriaRepository categoriaRepo ){
        this.produtoRepo = produtoRepo;
        this.categoriaRepo = categoriaRepo;
    }
    
    
    private void validarProduto(String nome, Double precoCusto, Categoria categoria){
        
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        
        if(precoCusto == null || precoCusto <= 0){
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");

        }
        
        if(categoria == null || categoriaRepo.buscarCategoriaPorId(categoria.getId()) == null){
           throw new IllegalArgumentException("A categoria informada não existe");

        }
    
    }
    
    
    public void incluirProduto(Produto produto){
        validarProduto(produto.getNome(), produto.getPrecoCusto(), produto.getCategoria());
        produtoRepo.incluirProduto(produto);
        
    }
    
    public void editarProduto(Produto produto, String novoNome, Double novoPrecoCusto, Categoria novaCategoria){
                validarProduto(novoNome, novoPrecoCusto, novaCategoria);
                produto.setNome(novoNome);
                produto.setPrecoCusto(novoPrecoCusto);
                produto.setCategoria(novaCategoria);
                
                produtoRepo.editarProduto(produto);

    }
    
    public List<Produto> listarProdutos(){
        
        return produtoRepo.exibirProdutos();
    }
    
     public List<Produto> buscarPorNome(String texto) {
       return produtoRepo.buscarProdutoPorNome(texto);
    }
    
    public List<Produto> buscarPorCategoria(String texto) {
        return produtoRepo.buscarPorCategoria(texto);    
    }
}
