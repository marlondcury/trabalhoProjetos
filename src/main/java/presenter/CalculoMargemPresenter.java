package presenter;

import java.awt.Window;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import service.CalculoProdutoService;
import view.CalculoMargemView;

public class CalculoMargemPresenter {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final CalculoMargemView view;
    private final CalculoProdutoService calculoService;

    public CalculoMargemPresenter(Window parent, CalculoProdutoService calculoService) {
        this.calculoService = calculoService;
        this.view = new CalculoMargemView(parent);

        view.getTxtData().setText(LocalDate.now().format(FORMATO_DATA));

        view.getBtnCalcular().addActionListener(e -> calcular());
        view.getBtnFechar().addActionListener(e -> view.dispose());   

        view.setLocationRelativeTo(parent);
        view.setVisible(true);   
    }

    private void calcular() {
        try {
            LocalDate data = lerData(view.getTxtData().getText());

            List<Produto> resultado = calculoService.calcular(data);

            preencherTabela(resultado);
            JOptionPane.showMessageDialog(view, "Cálculo realizado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void preencherTabela(List<Produto> produtos) {
        DefaultTableModel modelo = (DefaultTableModel) view.getTblResultado().getModel();
        modelo.setRowCount(0);
        for (Produto p : produtos) {
            modelo.addRow(new Object[] {
                p.getNome(),
                String.format("%.2f", p.getPrecoCusto()),
                p.getCategoria(),
                String.format("%.2f", p.getMargemLucroAtual()),
                String.format("%.2f", p.getPrecoVendaAtual())
            });
        }
    }

    private LocalDate lerData(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(texto.trim(), FORMATO_DATA);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida. Use o formato dd/mm/aaaa, por exemplo: "
                    + LocalDate.now().format(FORMATO_DATA));
        }
    }
}