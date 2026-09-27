
package service;

import java.util.List;
import model.Categoria;
import repository.ICategoriaRepository;
import repository.IProdutoRepository;


public class CategoriaService {
    
    private IProdutoRepository produtoRepo;
    private ICategoriaRepository categoriaRepo;
    
    public CategoriaService(IProdutoRepository produtoRepo, ICategoriaRepository categoriaRepo){
        this.produtoRepo = produtoRepo;
        this.categoriaRepo = categoriaRepo;
    }
    

    public void incluirCategoriaService(Categoria categoria) {
        if(categoria.getNome() == null || categoria.getNome().isBlank()){
           throw new IllegalArgumentException("O nome da categoria é obrigatório.");

        }
        
        if(categoriaRepo.buscarCategoriaPorNome(categoria.getNome()) !=null){
            throw new IllegalArgumentException("Já existe uma categoria com esse nome.");
        
        }
        
        if(categoria.getPercentualLucro() == null || categoria.getPercentualLucro() < 0){
            throw new IllegalArgumentException("O percentual deve ser maior que 0.");

        }
        categoriaRepo.incluirCategoria(categoria);
    }
    
    public void excluirCategoriaService(Categoria categoria) {
        if(categoria == null){
           throw new IllegalArgumentException("Selecione uma categoria para excluir.");

        }
        
        if(produtoRepo.existeProdutoComCategoria(categoria)){
            throw new IllegalStateException("Não é possível excluir: existem produtos associados a esta categoria.");
        
        }
        categoriaRepo.excluirCategoria(categoria);
    }
    
     
    public List<Categoria> exibirCategoriasService(){
        
        return categoriaRepo.exibirCategorias();
    }

}
