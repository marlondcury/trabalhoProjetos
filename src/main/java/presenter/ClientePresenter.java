package presenter;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import presenter.estado.CadastroContexto;
import presenter.estado.CadastroEstado;
import presenter.estado.VisualizacaoEstado;
import service.ClienteService;
import view.ClienteView;

public class ClientePresenter implements CadastroContexto {

    private final ClienteView view;
    private final ClienteService clienteService;
    private CadastroEstado estado;                // estado atual (padrão State)
    private List<Cliente> clientes = new ArrayList<>();

    public ClientePresenter(Window parent, ClienteService clienteService) {
        this.clienteService = clienteService;
        this.view = new ClienteView(parent);

        view.setTitle("Clientes");
        view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        view.getTblClientes().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        view.getTxtTipo().setEditable(false);
        view.getTxtTotalCompras().setEditable(false);

        view.getBtnNovo().addActionListener(e -> executar(() -> estado.novo()));
        view.getBtnEditar().addActionListener(e -> executar(() -> estado.editar()));
        view.getBtnExcluir().addActionListener(e -> executar(() -> estado.excluir()));
        view.getBtnSalvar().addActionListener(e -> executar(() -> estado.salvar()));
        view.getBtnCancelar().addActionListener(e -> executar(() -> estado.cancelar()));
        view.getBtnFechar().addActionListener(e -> view.dispose());
        view.getTblClientes().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && estado != null) {
                estado.selecionouLinha();
            }
        });

        carregarTabela();
        mudarEstado(new VisualizacaoEstado(this));
        // largura dos campos de texto
        for (javax.swing.JTextField campo : new javax.swing.JTextField[]{
                view.getTxtNome(), view.getTxtLogradouro(), view.getTxtBairro(),
                view.getTxtCidade(), view.getTxtUf(), view.getTxtTipo(),
                view.getTxtTotalCompras()}) {
            campo.setColumns(25);
        }
        view.pack();
        view.setLocationRelativeTo(parent);
        view.setVisible(true);
    }

    // ---------- State ----------

    @Override
    public void mudarEstado(CadastroEstado novoEstado) {
        this.estado = novoEstado;
        estado.entrar();
    }

    private void executar(Runnable acao) {
        try {
            acao.run();
        } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Clientes",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // ---------- Operações ----------

    @Override
    public void incluir() {
        Cliente novo = new Cliente(
                view.getTxtNome().getText().trim(),
                view.getTxtLogradouro().getText().trim(),
                view.getTxtBairro().getText().trim(),
                view.getTxtCidade().getText().trim(),
                view.getTxtUf().getText());
        clienteService.incluirCliente(novo);
        carregarTabela();
        selecionar(novo);
    }

    @Override
    public void salvarEdicao() {
        Cliente cliente = getSelecionado();
        clienteService.editarCliente(cliente,
                view.getTxtNome().getText().trim(),
                view.getTxtLogradouro().getText().trim(),
                view.getTxtBairro().getText().trim(),
                view.getTxtCidade().getText().trim(),
                view.getTxtUf().getText());
        carregarTabela();
        selecionar(cliente);
    }

    @Override
    public void excluirSelecionado() {
        Cliente cliente = getSelecionado();
        if (cliente == null) {
            throw new IllegalArgumentException("Selecione um cliente para excluir.");
        }
        int resp = JOptionPane.showConfirmDialog(view,
                "Excluir o cliente " + cliente.getNomeCliente() + "?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            clienteService.excluirCliente(cliente);
            carregarTabela();
        }
    }

    // ---------- Tela ----------

    @Override
    public void habilitarEdicao(boolean editar) {
        view.getTxtNome().setEditable(editar);
        view.getTxtLogradouro().setEditable(editar);
        view.getTxtBairro().setEditable(editar);
        view.getTxtCidade().setEditable(editar);
        view.getTxtUf().setEditable(editar);
        view.getTblClientes().setEnabled(!editar);
    }

    @Override
    public void habilitarBotoes(boolean novo, boolean editar, boolean excluir,
            boolean salvar, boolean cancelar) {
        view.getBtnNovo().setEnabled(novo);
        view.getBtnEditar().setEnabled(editar);
        view.getBtnExcluir().setEnabled(excluir);
        view.getBtnSalvar().setEnabled(salvar);
        view.getBtnCancelar().setEnabled(cancelar);
    }

    @Override
    public void limparCampos() {
        view.getTxtNome().setText("");
        view.getTxtLogradouro().setText("");
        view.getTxtBairro().setText("");
        view.getTxtCidade().setText("");
        view.getTxtUf().setText("");
        view.getTxtTipo().setText("");
        view.getTxtTotalCompras().setText("");
    }

    @Override
    public void exibirSelecionado() {
        Cliente c = getSelecionado();
        if (c == null) {
            limparCampos();
            return;
        }
        view.getTxtNome().setText(c.getNomeCliente());
        view.getTxtLogradouro().setText(c.getLogradouro());
        view.getTxtBairro().setText(c.getBairro());
        view.getTxtCidade().setText(c.getCidade());
        view.getTxtUf().setText(c.getUf());
        view.getTxtTipo().setText(String.valueOf(c.getTipoCliente()));
        view.getTxtTotalCompras().setText(String.format("%.2f", c.getTotalCompras()));
    }

    @Override
    public boolean temSelecionado() {
        return getSelecionado() != null;
    }

    // ---------- Tabela ----------

    private Cliente getSelecionado() {
        int linha = view.getTblClientes().getSelectedRow();
        return linha >= 0 ? clientes.get(linha) : null;
    }

    private void selecionar(Cliente cliente) {
        int linha = clientes.indexOf(cliente);
        if (linha >= 0) {
            view.getTblClientes().setRowSelectionInterval(linha, linha);
        }
    }

    private void carregarTabela() {
        clientes = clienteService.listarCliente();
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"Nome", "Logradouro", "Bairro", "Cidade", "UF", "Tipo", "Total de compras"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Cliente c : clientes) {
            modelo.addRow(new Object[]{c.getNomeCliente(), c.getLogradouro(), c.getBairro(),
                c.getCidade(), c.getUf(), c.getTipoCliente(),
                String.format("%.2f", c.getTotalCompras())});
        }
        view.getTblClientes().setModel(modelo);
    }
}