package app;

import model.Usuario;
import presenter.LoginPresenter;
import presenter.TelaPrincipalPresenter;
import repository.*;
import seeder.Seeder;
import service.*;

public class UmCasoDeUso {

    private static AutenticacaoService autenticacaoService;
    private static CategoriaService categoriaService;
    private static ProdutoService produtoService;
    private static CalculoProdutoService calculoService;
    private static HistoricoPrecoService historicoService;
    private static ClienteService clienteService;
    private static UsuarioService usuarioService;

    public static void main(String[] args) {
        // repositórios: um de cada, compartilhados por todos
        ICategoriaRepository categoriaRepo = new CategoriaRepositoryMemoria();
        IProdutoRepository produtoRepo = new ProdutoRepositoryMemoria();
        IHistoricoPrecoRepository historicoRepo = new HistoricoPrecoRepositoryMemoria();
        IClienteRepository clienteRepo = new ClienteRepositoryMemoria();
        IUsuarioRepository usuarioRepo = new UsuarioRepositoryMemoria();

        // services
categoriaService = new CategoriaService(produtoRepo, categoriaRepo);
produtoService = new ProdutoService(produtoRepo, categoriaRepo);
        calculoService = new CalculoProdutoService(produtoRepo, historicoRepo);
        historicoService = new HistoricoPrecoService(historicoRepo);
        clienteService = new ClienteService(usuarioRepo, clienteRepo);
        usuarioService = new UsuarioService(usuarioRepo, clienteRepo);
        autenticacaoService = new AutenticacaoService(usuarioRepo);

        new Seeder(categoriaService, produtoService, calculoService,
                clienteService, usuarioRepo).executar();

        iniciarSessao();
    }

    // login → tela principal; ao clicar em Sair, volta para cá
    private static void iniciarSessao() {
        LoginPresenter login = new LoginPresenter(autenticacaoService);
        Usuario logado = login.getUsuarioLogado();

        if (logado == null) {      // clicou em Fechar
            System.exit(0);
        }

        new TelaPrincipalPresenter(categoriaService, produtoService, calculoService,
                historicoService, clienteService, usuarioService,
                logado, () -> iniciarSessao());
    }
}