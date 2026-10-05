package presenter;

import java.awt.Window;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Produto;
import service.CategoriaService;
import service.ProdutoService;
import view.ProdutoFormView;

public class ProdutoFormPresenter {

    private final ProdutoFormView view;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final Produto produto;   

    public ProdutoFormPresenter(Window parent, ProdutoService produtoService,
                                CategoriaService categoriaService, Produto produto) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.produto = produto;
        this.view = new ProdutoFormView(parent);

        carregarCategorias();

        if (produto == null) {
            view.getCboCategoria().setSelectedIndex(-1);   
        } else {
            preencherCampos();                            
        }

        view.getBtnSalvar().addActionListener(e -> salvar());
        view.getBtnCancelar().addActionListener(e -> view.dispose());   

        view.setLocationRelativeTo(parent);   
        view.setVisible(true);                
    }

    private void carregarCategorias() {
        JComboBox<Categoria> combo = view.getCboCategoria();
        combo.removeAllItems();
        for (Categoria c : categoriaService.exibirCategoriasService()) {
            combo.addItem(c);  
        }
    }

    private void preencherCampos() {
        view.getTxtNome().setText(produto.getNome());
        view.getTxtPrecoCusto().setText(formatar(produto.getPrecoCusto()));
        view.getCboCategoria().setSelectedItem(produto.getCategoria());
        view.getTxtMargem().setText(formatar(produto.getMargemLucroAtual()));
        view.getTxtPrecoVenda().setText(formatar(produto.getPrecoVendaAtual()));
    }

    private void salvar() {
        try {
            String nome = view.getTxtNome().getText();
            Double precoCusto = lerPreco(view.getTxtPrecoCusto().getText());
            Categoria categoria = (Categoria) view.getCboCategoria().getSelectedItem();

            if (produto == null) {
                produtoService.incluirProduto(new Produto(nome, precoCusto, categoria));
            } else {
                produtoService.editarProduto(produto, nome, precoCusto, categoria);
            }

            JOptionPane.showMessageDialog(view, "Item salvo com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    private Double lerPreco(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        try {
            return Double.parseDouble(texto.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Preço de custo inválido. Use apenas números, por exemplo: 45,00");
        }
    }

    private String formatar(Double valor) {
        return valor == null ? "" : String.format("%.2f", valor);
    }
}