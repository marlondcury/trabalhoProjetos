package app;

import model.Cliente;
import model.Perfil;
import model.Usuario;
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
        
        Cliente ana = new Cliente("Ana Souza", "Rua das Flores, 123", "Centro", "Alegre", "ES");
System.out.println(ana.getTipoCliente());      // PRATA
ana.registrarCompras(100.0);
ana.registrarCompras(145.8);
System.out.println(ana.getTotalCompras());     // 245.8

Usuario u = new Usuario("Fernanda Alves", "fernanda", "fernanda@pocdelivery.com",
        "123", Perfil.ATENDENTE, null);
System.out.println(u.getStatusUsuario());      // Habilitado
u.desabilitarStatus();
System.out.println(u.getStatusUsuario());      // Desabilitado

     }
}
