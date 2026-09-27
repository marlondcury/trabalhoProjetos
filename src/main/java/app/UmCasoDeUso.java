package app;

import java.time.LocalDate;
import model.Categoria;
import model.HistoricoPreco;
import model.Produto;
import repository.CategoriaRepositoryMemoria;
import repository.HistoricoPrecoRepositoryMemoria;
import repository.ICategoriaRepository;
import repository.IHistoricoPrecoRepository;
import repository.IProdutoRepository;
import repository.ProdutoRepositoryMemoria;
import service.CategoriaService;
import service.ProdutoService;

public class UmCasoDeUso {

    public static void main(String[] args) {
        System.out.println("oi");

        Categoria c = new Categoria("Papelaria", 30.0);

        System.out.println(c);

        Produto p = new Produto("Lapis", 10.0, c);
        System.out.println(p.getNome());
        System.out.println(p.getCategoria());

        ICategoriaRepository repo = new CategoriaRepositoryMemoria();

        IProdutoRepository repoProduto = new ProdutoRepositoryMemoria();

        repo.incluirCategoria(new Categoria("Educacao", 25.0));
        repo.incluirCategoria(new Categoria("Lazer", 35.0));
        repo.incluirCategoria(new Categoria("Casa", 15.0));
        repo.incluirCategoria(c);

        Produto livro = new Produto("livro", 50.0, c);
        Produto livroDidatico = new Produto("livro Didatico", 50.0, c);
        Categoria lazer = repo.buscarCategoriaPorNome("Lazer");
        Categoria educacao = repo.buscarCategoriaPorNome("Educacao");

        Produto piscina = new Produto("piscina", 5000.0, lazer);

        repoProduto.incluirProduto(p);
        repoProduto.incluirProduto(livro);
        repoProduto.incluirProduto(livroDidatico);
        repoProduto.incluirProduto(piscina);

        for (Categoria c1 : repo.exibirCategorias()) {
            System.out.println(c1.getId() + " - " + c1.getNome());

        }

        System.out.println("--------------");
        for (Produto p1 : repoProduto.exibirProdutos()) {
            System.out.println(p1.getId() + " - " + p1.getNome());

        }

        System.out.println("Id 99: " + repo.buscarCategoriaPorId(99));
        System.out.println("Tamanho antes excluir: " + repo.exibirCategorias().size());

        Categoria casa = repo.buscarCategoriaPorNome("casa");
        repo.excluirCategoria(casa);
        System.out.println("Tamanho depois  excluir: " + repo.exibirCategorias().size());
        System.out.println(repoProduto.buscarProdutoPorNome("LIVRO").size());
        System.out.println(repoProduto.buscarPorCategoria("PAP").size());
        System.out.println(repoProduto.existeProdutoComCategoria(lazer));
        System.out.println(repoProduto.existeProdutoComCategoria(educacao));

        IHistoricoPrecoRepository repoHist = new HistoricoPrecoRepositoryMemoria();

        repoHist.incluirHistorico(new HistoricoPreco(LocalDate.of(2026, 9, 1), 30.0, 13.00, p));
        repoHist.incluirHistorico(new HistoricoPreco(LocalDate.of(2026, 9, 20), 30.0, 13.00, p));
        repoHist.incluirHistorico(new HistoricoPreco(LocalDate.of(2026, 9, 10), 35.0, 13.50, p));
        System.out.println(repoHist.exibirHistoricoProdutos(p).size());
        System.out.println(repoHist.exibirHistoricoProdutos(livro).size());
        System.out.println(repoHist.exibirUltimaData());

        CategoriaService categoriaService = new CategoriaService(repoProduto, repo);
        System.out.println("--- Teste 1: incluir válida ---");

        Categoria papelaria = new Categoria("Mesa", 30.0);
        try {
            categoriaService.excluirCategoriaService(c);
            System.out.println("EXcluída com sucesso!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("Total: " + categoriaService.exibirCategoriasService().size());

        ProdutoService produtoService = new ProdutoService(repoProduto, repo);

        try {
            produtoService.editarProduto(p, "Lapis", -10.0, c);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());

            System.out.println("Preço depois: " + p.getPrecoCusto());

        }

        System.out.println(1.20 * 1.30);
        System.out.println(45.0 * (1 + 25.0 / 100));

        LocalDate a = LocalDate.of(2026, 9, 17);
        LocalDate b = LocalDate.of(2026, 9, 27);
        System.out.println(java.time.temporal.ChronoUnit.DAYS.between(a, b));
        System.out.println(java.time.temporal.ChronoUnit.DAYS.between(b, a));
    }
}
