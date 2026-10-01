
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

        jMenuBar1 = new javax.swing.JMenuBar();
        mnuDados = new javax.swing.JMenu();
        mnuIncluirProdutos = new javax.swing.JMenuItem();
        mnuBuscarProdutos = new javax.swing.JMenuItem();
        mnuCategorias = new javax.swing.JMenuItem();
        mnuCalcularMargem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Supermercado");

        mnuDados.setText("Dados");

        mnuIncluirProdutos.setText("Incluir produtos");
        mnuDados.add(mnuIncluirProdutos);

        mnuBuscarProdutos.setText("Buscar produtos");
        mnuDados.add(mnuBuscarProdutos);

        mnuCategorias.setText("Categorias");
        mnuCategorias.setActionCommand("Categorias");
        mnuDados.add(mnuCategorias);

        mnuCalcularMargem.setText("Calcular margem de lucro");
        mnuDados.add(mnuCalcularMargem);

        jMenuBar1.add(mnuDados);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JMenuItem getMnuBuscarProdutos() {
        return mnuBuscarProdutos;
    }

    public JMenuItem getMnuCalcularMargem() {
        return mnuCalcularMargem;
    }

    public JMenuItem getMnuCategorias() {
        return mnuCategorias;
    }

    public JMenuItem getMnuIncluirProdutos() {
        return mnuIncluirProdutos;
    }

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mnuBuscarProdutos;
    private javax.swing.JMenuItem mnuCalcularMargem;
    private javax.swing.JMenuItem mnuCategorias;
    private javax.swing.JMenu mnuDados;
    private javax.swing.JMenuItem mnuIncluirProdutos;
    // End of variables declaration//GEN-END:variables
}
