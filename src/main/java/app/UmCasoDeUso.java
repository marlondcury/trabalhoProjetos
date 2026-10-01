package app;

import presenter.TelaPrincipalPresenter;
import repository.CategoriaRepositoryMemoria;
import repository.HistoricoPrecoRepositoryMemoria;
import repository.ICategoriaRepository;
import repository.IHistoricoPrecoRepository;
import repository.IProdutoRepository;
import repository.ProdutoRepositoryMemoria;
import seeder.Seeder;
import service.CalculoProdutoService;
import service.CategoriaService;
import service.HistoricoPrecoService;
import service.ProdutoService;

public class UmCasoDeUso {

    public static void main(String[] args) {



        ICategoriaRepository categoriaRepo = new CategoriaRepositoryMemoria();
        IProdutoRepository produtoRepo = new ProdutoRepositoryMemoria();
        IHistoricoPrecoRepository historicoRepo = new HistoricoPrecoRepositoryMemoria();

        CategoriaService categoriaService = new CategoriaService(produtoRepo, categoriaRepo);
        ProdutoService produtoService = new ProdutoService(produtoRepo, categoriaRepo);
        CalculoProdutoService calculoService = new CalculoProdutoService(produtoRepo, historicoRepo);
        HistoricoPrecoService historicoService = new HistoricoPrecoService(historicoRepo);

        Seeder seeder = new Seeder(categoriaService, produtoService, calculoService);
        seeder.executar();
        
        new TelaPrincipalPresenter(categoriaService, produtoService, calculoService, historicoService);        

     }
}
