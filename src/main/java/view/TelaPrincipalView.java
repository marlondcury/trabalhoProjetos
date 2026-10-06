
package view;

import javax.swing.JMenuItem;


public class TelaPrincipalView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipalView.class.getName());

    public TelaPrincipalView() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuarioLogado = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuOperacao = new javax.swing.JMenu();
        mnuDados = new javax.swing.JMenu();
        mnuClientes = new javax.swing.JMenuItem();
        mnuEntregadores = new javax.swing.JMenuItem();
        mnuTaxasEntrega = new javax.swing.JMenuItem();
        mnuIncluirProdutos = new javax.swing.JMenuItem();
        mnuBuscarProdutos = new javax.swing.JMenuItem();
        mnuCategorias = new javax.swing.JMenuItem();
        mnuCalcularMargem = new javax.swing.JMenuItem();
        mnuUsuarios = new javax.swing.JMenu();
        mnuCadastroUsuarios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Supermercado");

        lblUsuarioLogado.setText("Usuário:");

        btnSair.setText("Sair");

        mnuOperacao.setText("Operação");
        jMenuBar1.add(mnuOperacao);

        mnuDados.setText("Dados");

        mnuClientes.setText("Clientes");
        mnuDados.add(mnuClientes);

        mnuEntregadores.setText("Entregadores");
        mnuDados.add(mnuEntregadores);

        mnuTaxasEntrega.setText("Taxas de entrega");
        mnuDados.add(mnuTaxasEntrega);

        mnuIncluirProdutos.setText("Incluir Produtos");
        mnuDados.add(mnuIncluirProdutos);

        mnuBuscarProdutos.setText("Buscar Produtos");
        mnuDados.add(mnuBuscarProdutos);

        mnuCategorias.setText("Categorias");
        mnuDados.add(mnuCategorias);

        mnuCalcularMargem.setText("Calcular margem de lucro");
        mnuDados.add(mnuCalcularMargem);

        jMenuBar1.add(mnuDados);

        mnuUsuarios.setText("Usuários");

        mnuCadastroUsuarios.setText("Cadastro de usuários");
        mnuUsuarios.add(mnuCadastroUsuarios);

        jMenuBar1.add(mnuUsuarios);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblUsuarioLogado)
                .addGap(85, 85, 85)
                .addComponent(btnSair)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioLogado)
                    .addComponent(btnSair))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        public JMenuItem getMnuIncluirProdutos() { return mnuIncluirProdutos; }
    public JMenuItem getMnuBuscarProdutos() { return mnuBuscarProdutos; }
    public JMenuItem getMnuCategorias() { return mnuCategorias; }
    public JMenuItem getMnuCalcularMargem() { return mnuCalcularMargem; }
    public JMenuItem getMnuClientes() { return mnuClientes; }
    public JMenuItem getMnuEntregadores() { return mnuEntregadores; }
    public JMenuItem getMnuTaxasEntrega() { return mnuTaxasEntrega; }
    public JMenuItem getMnuCadastroUsuarios() { return mnuCadastroUsuarios; }
    public javax.swing.JMenu getMnuOperacao() { return mnuOperacao; }
    public javax.swing.JMenu getMnuDados() { return mnuDados; }
    public javax.swing.JMenu getMnuUsuarios() { return mnuUsuarios; }
    public javax.swing.JLabel getLblUsuarioLogado() { return lblUsuarioLogado; }
    public javax.swing.JButton getBtnSair() { return btnSair; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSair;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblUsuarioLogado;
    private javax.swing.JMenuItem mnuBuscarProdutos;
    private javax.swing.JMenuItem mnuCadastroUsuarios;
    private javax.swing.JMenuItem mnuCalcularMargem;
    private javax.swing.JMenuItem mnuCategorias;
    private javax.swing.JMenuItem mnuClientes;
    private javax.swing.JMenu mnuDados;
    private javax.swing.JMenuItem mnuEntregadores;
    private javax.swing.JMenuItem mnuIncluirProdutos;
    private javax.swing.JMenu mnuOperacao;
    private javax.swing.JMenuItem mnuTaxasEntrega;
    private javax.swing.JMenu mnuUsuarios;
    // End of variables declaration//GEN-END:variables
}
