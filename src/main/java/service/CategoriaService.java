package service;

import java.util.List;
import model.Categoria;
import repository.ICategoriaRepository;
import repository.IProdutoRepository;

public class CategoriaService {

    private final IProdutoRepository produtoRepo;
    private final ICategoriaRepository categoriaRepo;

    public CategoriaService(IProdutoRepository produtoRepo, ICategoriaRepository categoriaRepo) {
        this.produtoRepo = produtoRepo;
        this.categoriaRepo = categoriaRepo;
    }

    private void validarDados(String nome, Double percentual) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório.");
        }
        if (percentual == null || percentual < 0) {
            throw new IllegalArgumentException("O percentual de lucro é obrigatório e não pode ser negativo.");
        }
    }

    public void incluirCategoriaService(Categoria categoria) {
        validarDados(categoria.getNome(), categoria.getPercentualLucro());

        if (categoriaRepo.buscarCategoriaPorNome(categoria.getNome()) != null) {
            throw new IllegalArgumentException("Já existe uma categoria com esse nome.");
        }
        categoriaRepo.incluirCategoria(categoria);
    }

    public void editarCategoriaService(Categoria categoria, String novoNome, Double novoPercentual) {
        if (categoria == null) {
            throw new IllegalArgumentException("Selecione uma categoria para editar.");
        }
        validarDados(novoNome, novoPercentual);

        Categoria existente = categoriaRepo.buscarCategoriaPorNome(novoNome);
        if (existente != null && existente != categoria) {
            throw new IllegalArgumentException("Já existe uma categoria com esse nome.");
        }

        categoria.setNome(novoNome);
        categoria.setPercentualLucro(novoPercentual);
        categoriaRepo.editarCategoria(categoria);
    }

    public void excluirCategoriaService(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Selecione uma categoria para excluir.");
        }
        if (produtoRepo.existeProdutoComCategoria(categoria)) {
            throw new IllegalStateException("Não é possível excluir: existem produtos associados a esta categoria.");
        }
        categoriaRepo.excluirCategoria(categoria);
    }

    public List<Categoria> exibirCategoriasService() {
        return categoriaRepo.exibirCategorias();
    }
}