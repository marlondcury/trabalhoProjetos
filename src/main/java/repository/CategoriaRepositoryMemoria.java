
package repository;

import java.util.ArrayList;
import java.util.List;
import model.Categoria;


public class CategoriaRepositoryMemoria implements  ICategoriaRepository{
    
    
    private int proximoId = 1;
    
    private List<Categoria> categorias = new ArrayList<>();
    
    
    @Override
    public List<Categoria> exibirCategorias(){
        
        List<Categoria> copiaCategorias= new ArrayList<>();
    
        for(Categoria categoria : categorias){
            copiaCategorias.add(categoria);
        }    
        return copiaCategorias;
    }

    @Override
    public void incluirCategoria(Categoria categoria) {
        categoria.setId(proximoId);
        proximoId++;
        categorias.add(categoria);
    }

    @Override
    public void editarCategoria(Categoria categoria) {
    // Em memória, o objeto já foi alterado diretamente,
    // pois é a mesma referência guardada na lista.
    }

    @Override
    public void excluirCategoria(Categoria categoria) {
        categorias.remove(categoria);
     }
    
    @Override
    public Categoria buscarCategoriaPorId(int id) {
        for(Categoria categoria : categorias){
            if(categoria.getId() == id){
                return categoria;
                
            }
        }
        return null;   
    }

    @Override
    public Categoria buscarCategoriaPorNome(String nome) {
        for(Categoria categoria : categorias){
            if(categoria.getNome().equalsIgnoreCase(nome)){
                return categoria;
                
            }
        }
        return null;
    }
    
}
