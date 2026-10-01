package presenter;

import java.awt.Window;
import model.Produto;
import service.CategoriaService;
import service.HistoricoPrecoService;
import service.ProdutoService;
import view.ProdutoVisualizacaoView;

public class ProdutoVisualizacaoPresenter {

    private final ProdutoVisualizacaoView view;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final Produto produto;
    private final HistoricoPrecoService historicoService;

    public ProdutoVisualizacaoPresenter(Window parent, ProdutoService produtoService,
                                        CategoriaService categoriaService, HistoricoPrecoService historicoService, Produto produto) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.historicoService = historicoService;                      
        this.produto = produto;
        this.view = new ProdutoVisualizacaoView(parent);

        preencherCampos();

        view.getBtnEditar().addActionListener(e -> editar());
        view.getBtnFechar().addActionListener(e -> view.dispose());
        view.getBtnHistorico().addActionListener(e ->
            new HistoricoPrecoPresenter(view, historicoService, produto));
        view.setLocationRelativeTo(parent);
        view.setVisible(true);  
    }

    private void preencherCampos() {
        view.getTxtNome().setText(produto.getNome());
        view.getTxtPrecoCusto().setText(formatar(produto.getPrecoCusto()));
        view.getTxtCategoria().setText(produto.getCategoria().getNome());
        view.getTxtMargem().setText(formatar(produto.getMargemLucroAtual()));
        view.getTxtPrecoVenda().setText(formatar(produto.getPrecoVendaAtual()));
    }

    private void editar() {
        new ProdutoFormPresenter(view, produtoService, categoriaService, produto);
        preencherCampos();   
    }

    private String formatar(Double valor) {
        return valor == null ? "" : String.format("%.2f", valor);
    }
}