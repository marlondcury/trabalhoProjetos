package presenter;

import java.awt.Window;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import model.HistoricoPreco;
import model.Produto;
import service.HistoricoPrecoService;
import view.HistoricoPrecoView;

public class HistoricoPrecoPresenter {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final HistoricoPrecoView view;

    public HistoricoPrecoPresenter(Window parent, HistoricoPrecoService historicoService, Produto produto) {
        this.view = new HistoricoPrecoView(parent);

        view.getTxtProduto().setText(produto.getNome());
        view.getTxtCategoria().setText(produto.getCategoria().getNome());

        DefaultTableModel modelo = (DefaultTableModel) view.getTblHistorico().getModel();
        modelo.setRowCount(0);
        for (HistoricoPreco h : historicoService.listarPorProduto(produto)) {
            modelo.addRow(new Object[] {
                h.getData().format(FORMATO_DATA),
                String.format("%.2f", h.getPercentualLucro()),
                String.format("%.2f", h.getPrecoVenda())
            });
        }

        view.getBtnFechar().addActionListener(e -> view.dispose());   

        view.setLocationRelativeTo(parent);
        view.setVisible(true);   
    }
}