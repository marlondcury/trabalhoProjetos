
package repository;

import java.util.List;
import model.Categoria;
import model.Produto;
import java.util.ArrayList;


public class ProdutoRepositoryMemoria implements IProdutoRepository {
    
    private int proximoId = 1;
    private List<Produto> produtos = new ArrayList<>();

    @Override
    public List<Produto> exibirProdutos() {
        
         List<Produto> copiaProdutos = new ArrayList<>();
        
        for(Produto produto : produtos){
            
            copiaProdutos.add(produto);
        }
        
        return copiaProdutos;   
    }

    @Override
    public void incluirProduto(Produto produto) {
        produto.setId(proximoId);
        proximoId++;
        produtos.add(produto);
    }

    @Override
    public void editarProduto(Produto produto) {
           // Em memória, o objeto já foi alterado diretamente,
    // pois é a mesma referência guardada na lista.
    }

    @Override
    public void excluirProduto(Produto produto) {
        produtos.remove(produto);
    }

    @Override
    public Produto buscarProdutoPorId(int id) {
        for(Produto produto : produtos){
            if(produto.getId() == id){
            return produto;}
        }
        return null;
    }

    @Override
    public List<Produto> buscarProdutoPorNome(String nomeProduto) {
         List<Produto> copiaProdutos = new ArrayList<>();
        for(Produto produto : produtos){
            if(produto.getNome().toLowerCase().contains(nomeProduto.toLowerCase())){
                copiaProdutos.add(produto);}
        }
        return copiaProdutos;

    }

    @Override
    public boolean existeProdutoComCategoria(Categoria categoria) {
        for(Produto produto : produtos){
            if(produto.getCategoria().equals(categoria)){
                return true;}
        }
        return false;
    }

    @Override
    public List<Produto> buscarPorCategoria(String nomeCategoria) {
        
         List<Produto> copiaProdutos = new ArrayList<>();

        for(Produto produto : produtos){
            if(produto.getCategoria().getNome().toLowerCase().contains(nomeCategoria.toLowerCase())){
                copiaProdutos.add(produto);
            }
        }
        return copiaProdutos;
    }
    
}
