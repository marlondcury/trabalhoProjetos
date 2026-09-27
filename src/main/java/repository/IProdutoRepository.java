
package repository;

import java.util.List;
import model.Categoria;
import model.Produto;


public interface IProdutoRepository {
    
    public List<Produto> exibirProdutos();
    
    public void incluirProduto(Produto produto);
    
    public void editarProduto(Produto produto);
    
    public void excluirProduto(Produto produto);
    
    public Produto buscarProdutoPorId(int id);
    
    public List<Produto> buscarProdutoPorNome(String nome);
    
    public boolean existeProdutoComCategoria(Categoria categoria);
    
    public List<Produto> buscarPorCategoria(String nomeCategoria);
    
}
