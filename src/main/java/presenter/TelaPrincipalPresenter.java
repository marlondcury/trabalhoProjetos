package presenter;


import service.CalculoProdutoService;
import service.CategoriaService;
import service.HistoricoPrecoService;
import service.ProdutoService;
import view.TelaPrincipalView;



public class TelaPrincipalPresenter {

    private final TelaPrincipalView view;
    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;
    private final CalculoProdutoService calculoService;
    private final HistoricoPrecoService historicoService;


    public TelaPrincipalPresenter(CategoriaService categoriaService,
                                  ProdutoService produtoService,
                                  CalculoProdutoService calculoService,
                                  HistoricoPrecoService historicoService) {

        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
        this.calculoService = calculoService;
        this.historicoService = historicoService;
        this.view = new TelaPrincipalView();

        view.getMnuIncluirProdutos().addActionListener(e -> abrirInclusaoProduto());
        view.getMnuBuscarProdutos().addActionListener(e -> abrirBuscaProdutos());
        view.getMnuCalcularMargem().addActionListener(e -> abrirCalculoMargem());
        view.getMnuCategorias().addActionListener(e -> abrirCategorias());

        view.setVisible(true);
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

}