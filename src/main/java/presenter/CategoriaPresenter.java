package presenter;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import service.CategoriaService;
import view.CategoriaView;

public class CategoriaPresenter {

    private enum Modo { VISUALIZACAO, INCLUSAO, EDICAO }

    private final CategoriaView view;
    private final CategoriaService categoriaService;
    private List<Categoria> categoriasExibidas = new ArrayList<>();
    private Categoria selecionada;   
    private Modo modo;

    public CategoriaPresenter(Window parent, CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
        this.view = new CategoriaView(parent);

        view.getBtnNovo().addActionListener(e -> novo());
        view.getBtnEditar().addActionListener(e -> editar());
        view.getBtnExcluir().addActionListener(e -> excluir());
        view.getBtnSalvar().addActionListener(e -> salvar());
        view.getBtnCancelar().addActionListener(e -> cancelar());
        view.getBtnFechar().addActionListener(e -> view.dispose());

        view.getTblCategorias().getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;   
            }
            int linha = view.getTblCategorias().getSelectedRow();
            selecionada = (linha == -1) ? null : categoriasExibidas.get(linha);
            mostrar(selecionada);
        });

        aplicarModo(Modo.VISUALIZACAO);
        carregarTabela();
        if (!categoriasExibidas.isEmpty()) {
            selecionarLinha(categoriasExibidas.get(0));   
        }

        view.setLocationRelativeTo(parent);
        view.setVisible(true);  
    }

    // ---------------- Estados da tela ----------------

    private void aplicarModo(Modo novoModo) {
        this.modo = novoModo;
        boolean visualizando = (novoModo == Modo.VISUALIZACAO);

        view.getTxtNome().setEditable(!visualizando);
        view.getTxtPercentual().setEditable(!visualizando);

        view.getBtnNovo().setEnabled(visualizando);
        view.getBtnEditar().setEnabled(visualizando);
        view.getBtnExcluir().setEnabled(visualizando);
        view.getBtnFechar().setEnabled(visualizando);
        view.getBtnSalvar().setEnabled(!visualizando);
        view.getBtnCancelar().setEnabled(!visualizando);

        view.getTblCategorias().setEnabled(visualizando);

        view.getLblModo().setText(switch (novoModo) {
            case VISUALIZACAO -> "Modo: Visualização";
            case INCLUSAO -> "Modo: Inclusão";
            case EDICAO -> "Modo: Edição";
        });
    }

    // ---------------- Ações dos botões ----------------

    private void novo() {
        aplicarModo(Modo.INCLUSAO);
        view.getTxtNome().setText("");
        view.getTxtPercentual().setText("");
        view.getTxtNome().requestFocus();
    }

    private void editar() {
        if (selecionada == null) {
            JOptionPane.showMessageDialog(view, "Selecione uma categoria na tabela.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        aplicarModo(Modo.EDICAO);
        view.getTxtNome().requestFocus();
    }

    private void salvar() {
        try {
            String nome = view.getTxtNome().getText();
            Double percentual = lerPercentual(view.getTxtPercentual().getText());
            Categoria salva;

            if (modo == Modo.INCLUSAO) {
                salva = new Categoria(nome, percentual);
                categoriaService.incluirCategoriaService(salva);
            } else {
                categoriaService.editarCategoriaService(selecionada, nome, percentual);
                salva = selecionada;
            }

            JOptionPane.showMessageDialog(view, "Item salvo com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            aplicarModo(Modo.VISUALIZACAO);
            carregarTabela();
            selecionarLinha(salva);

        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelar() {
        aplicarModo(Modo.VISUALIZACAO);
        mostrar(selecionada);
    }

    private void excluir() {
        if (selecionada == null) {
            JOptionPane.showMessageDialog(view, "Selecione uma categoria na tabela.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(view, "Deseja realmente excluir?",
                "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);
        if (resposta != JOptionPane.YES_OPTION) {
            return;   // clicou em "Não" ou fechou a janela
        }

        try {
            String nome = selecionada.getNome();
            categoriaService.excluirCategoriaService(selecionada);
            JOptionPane.showMessageDialog(view, "Item \"" + nome + "\" excluído com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarTabela();

        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------------- Apoio ----------------

    private void carregarTabela() {
        categoriasExibidas = categoriaService.exibirCategoriasService();
        DefaultTableModel modelo = (DefaultTableModel) view.getTblCategorias().getModel();
        modelo.setRowCount(0);
        for (Categoria c : categoriasExibidas) {
            modelo.addRow(new Object[] { c.getNome(), String.format("%.2f", c.getPercentualLucro()) });
        }
    }

    private void selecionarLinha(Categoria categoria) {
        int indice = categoriasExibidas.indexOf(categoria);
        if (indice >= 0) {
            view.getTblCategorias().setRowSelectionInterval(indice, indice);
        }
    }

    private void mostrar(Categoria categoria) {
        if (categoria == null) {
            view.getTxtNome().setText("");
            view.getTxtPercentual().setText("");
        } else {
            view.getTxtNome().setText(categoria.getNome());
            view.getTxtPercentual().setText(String.format("%.2f", categoria.getPercentualLucro()));
        }
    }

    private Double lerPercentual(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        try {
            return Double.parseDouble(texto.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Percentual inválido. Use apenas números, por exemplo: 25,00");
        }
    }
}