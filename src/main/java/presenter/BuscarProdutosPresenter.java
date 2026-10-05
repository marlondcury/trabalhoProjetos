package presenter;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import service.CategoriaService;
import service.HistoricoPrecoService;
import service.ProdutoService;
import view.BuscarProdutosView;

public class BuscarProdutosPresenter {

    private final BuscarProdutosView view;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final HistoricoPrecoService historicoService;


    private List<Produto> produtosExibidos = new ArrayList<>();

    public BuscarProdutosPresenter(JFrame parent, ProdutoService produtoService, CategoriaService categoriaService,
            HistoricoPrecoService historicoService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;    
        this.historicoService = historicoService;
        this.view = new BuscarProdutosView(parent, true);   

        view.getBtnBuscar().addActionListener(e -> buscar());
        view.getBtnFechar().addActionListener(e -> view.dispose());
view.getBtnNovo().addActionListener(e -> {
    new ProdutoFormPresenter(view, produtoService, categoriaService, null);
    buscar();
});
view.getBtnVisualizar().addActionListener(e -> visualizar());

        view.getBtnVisualizar().setEnabled(false);

        view.getTblProdutos().getSelectionModel().addListSelectionListener(e -> {
            boolean temSelecao = view.getTblProdutos().getSelectedRow() != -1;
            view.getBtnVisualizar().setEnabled(temSelecao);
        });

        buscar();

        view.setVisible(true);
    }

    private void buscar() {
        String texto = view.getTxtBusca().getText();
        int opcao = view.getCboBuscaPor().getSelectedIndex();   

        if (opcao == 0) {
            produtosExibidos = produtoService.buscarPorNome(texto);
        } else {
            produtosExibidos = produtoService.buscarPorCategoria(texto);
        }

        DefaultTableModel modelo = (DefaultTableModel) view.getTblProdutos().getModel();
        modelo.setRowCount(0);

        for (Produto p : produtosExibidos) {
            modelo.addRow(new Object[] {
                p.getNome(),
                formatar(p.getPrecoCusto()),
                p.getCategoria(),                  
                formatar(p.getMargemLucroAtual()),
                formatar(p.getPrecoVendaAtual())
            });
        }
    }

    private void visualizar() {
        int linha = view.getTblProdutos().getSelectedRow();
        if (linha == -1) {
            return;  
        }
        Produto selecionado = produtosExibidos.get(linha);
        new ProdutoVisualizacaoPresenter(view, produtoService, categoriaService, historicoService, selecionado);

    }

    private String formatar(Double valor) {
        if (valor == null) {
            return "";
        }
        return String.format("%.2f", valor);
    }
}