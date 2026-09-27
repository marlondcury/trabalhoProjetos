
package repository;

import java.util.List;
import model.Categoria;


public interface ICategoriaRepository {
    
    
    public List<Categoria> exibirCategorias();
    
    public void incluirCategoria(Categoria categoria);
    
    public void editarCategoria(Categoria categoria);
    
    public void excluirCategoria(Categoria categoria);
    
    public Categoria buscarCategoriaPorId(int id);
    
    public Categoria buscarCategoriaPorNome(String nome);

}
