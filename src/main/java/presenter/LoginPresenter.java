package presenter;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import model.Usuario;
import service.AutenticacaoService;
import view.LoginView;

public class LoginPresenter {

    private final LoginView view;
    private final AutenticacaoService autenticacaoService;
    private Usuario usuarioLogado;  

    public LoginPresenter(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
        this.view = new LoginView(null);

        view.setTitle("Login");
        view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        view.getRootPane().setDefaultButton(view.getBtnEntrar()); 

        view.getBtnEntrar().addActionListener(e -> entrar());
        view.getBtnFechar().addActionListener(e -> view.dispose());

        view.pack();
        view.setLocationRelativeTo(null);  
        view.setVisible(true);             
    }

    private void entrar() {
        try {
            String login = view.getTxtLogin().getText().trim();
            String senha = new String(view.getTxtSenha().getPassword());
            usuarioLogado = autenticacaoService.autenticarUsuario(login, senha);
            view.dispose();
        } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Login",
                    JOptionPane.ERROR_MESSAGE);
            view.getTxtSenha().setText("");
        }
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}