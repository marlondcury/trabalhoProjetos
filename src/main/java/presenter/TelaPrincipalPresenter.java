package presenter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Perfil;
import model.Usuario;
import service.CalculoProdutoService;
import service.CategoriaService;
import service.ClienteService;
import service.HistoricoPrecoService;
import service.ProdutoService;
import service.UsuarioService;
import view.TelaPrincipalView;

public class TelaPrincipalPresenter {

    private final TelaPrincipalView view;
    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;
    private final CalculoProdutoService calculoService;
    private final HistoricoPrecoService historicoService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;
    private final Usuario logado;
    private final Runnable aoSair;

    public TelaPrincipalPresenter(CategoriaService categoriaService, ProdutoService produtoService,
            CalculoProdutoService calculoService, HistoricoPrecoService historicoService,
            ClienteService clienteService, UsuarioService usuarioService,
            Usuario logado, Runnable aoSair) {

        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
        this.calculoService = calculoService;
        this.historicoService = historicoService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
        this.logado = logado;
        this.aoSair = aoSair;
        this.view = new TelaPrincipalView();

        view.getLblUsuarioLogado().setText("Usuário: " + logado.getNomeCompleto()
                + " (" + logado.getPerfil() + ")");

        view.getMnuIncluirProdutos().addActionListener(e -> abrirInclusaoProduto());
        view.getMnuBuscarProdutos().addActionListener(e -> abrirBuscaProdutos());
        view.getMnuCalcularMargem().addActionListener(e -> abrirCalculoMargem());
        view.getMnuCategorias().addActionListener(e -> abrirCategorias());
        view.getMnuClientes().addActionListener(e -> abrirClientes());
        view.getMnuCadastroUsuarios().addActionListener(e -> abrirUsuarios());
        view.getMnuEntregadores().addActionListener(e -> emDesenvolvimento());
        view.getMnuTaxasEntrega().addActionListener(e -> emDesenvolvimento());
        view.getBtnSair().addActionListener(e -> sair());

        aplicarPermissoes();

        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setVisible(true);
    }

    private void aplicarPermissoes() {
        Perfil perfil = logado.getPerfil();
        view.getMnuUsuarios().setVisible(perfil == Perfil.ADMINISTRADOR);
        view.getMnuDados().setVisible(perfil != Perfil.CLIENTE);
    }

    private void sair() {
        view.dispose();
        aoSair.run();
    }

    private void abrirInclusaoProduto() {
        new ProdutoFormPresenter(view, produtoService, categoriaService, null);
    }

    private void abrirBuscaProdutos() {
        new BuscarProdutosPresenter(view, produtoService, categoriaService, historicoService);
    }

    private void abrirCategorias() {
        new CategoriaPresenter(view, categoriaService);
    }

    private void abrirCalculoMargem() {
        new CalculoMargemPresenter(view, calculoService);
    }

    private void abrirClientes() {
       new ClientePresenter(view, clienteService);
    }

    private void abrirUsuarios() {
        emDesenvolvimento();   // trocamos depois de Clientes
    }

    private void emDesenvolvimento() {
        JOptionPane.showMessageDialog(view, "Funcionalidade em desenvolvimento.");
    }
}