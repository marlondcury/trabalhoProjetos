package service;

import java.util.List;
import model.Cliente;
import model.Perfil;
import model.Usuario;
import repository.IClienteRepository;
import repository.IUsuarioRepository;

public class UsuarioService {

    private final IUsuarioRepository usuarioRepo;
    private final IClienteRepository clienteRepo;

    public UsuarioService(IUsuarioRepository usuarioRepo, IClienteRepository clienteRepo) {
        this.usuarioRepo = usuarioRepo;
        this.clienteRepo = clienteRepo;
    }

    // ---------- Permissões ----------

    private void exigirLogado(Usuario logado) {
        if (logado == null) {
            throw new IllegalStateException("Nenhum usuário logado.");
        }
    }

    private void exigirAdministrador(Usuario logado) {
        exigirLogado(logado);
        if (logado.getPerfil() != Perfil.ADMINISTRADOR) {
            throw new IllegalStateException("Somente o Administrador pode realizar esta operação.");
        }
    }

    private void exigirSelecionado(Usuario alvo) {
        if (alvo == null) {
            throw new IllegalArgumentException("Selecione um usuário.");
        }
    }

    // ---------- Validação de dados ----------

    private void validarUsuario(String nomeCompleto, String nomeUsuario, String email,
            String senha, String confirmacaoSenha, Perfil perfil, Cliente cliente) {

        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            throw new IllegalArgumentException("O nome completo não pode ser vazio.");
        }
        if (nomeUsuario == null || nomeUsuario.isBlank()) {
            throw new IllegalArgumentException("O nome de usuário não pode ser vazio.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("O e-mail não pode ser vazio.");
        }
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        }
        if (!senha.equals(confirmacaoSenha)) {
            throw new IllegalArgumentException("A senha e a confirmação não conferem.");
        }
        if (perfil == null) {
            throw new IllegalArgumentException("Selecione um perfil.");
        }
        if (perfil == Perfil.CLIENTE) {
            if (cliente == null) {
                throw new IllegalArgumentException("Usuário com perfil Cliente precisa de um cliente associado.");
            }
            if (clienteRepo.buscarClientePorId(cliente.getId()) == null) {
                throw new IllegalArgumentException("Cliente não encontrado.");
            }
        } else if (cliente != null) {
            throw new IllegalArgumentException("Somente usuários com perfil Cliente podem ter cliente associado.");
        }
    }

    private void validarNomeUnico(String nomeUsuario, Usuario ignorar) {
        Usuario encontrado = usuarioRepo.buscarNomeUsuario(nomeUsuario.trim());
        if (encontrado != null && encontrado != ignorar) {
            throw new IllegalArgumentException("Já existe um usuário com esse nome de usuário.");
        }
    }

    // ---------- Operações ----------

    public void incluirUsuario(Usuario logado, Usuario novo, String confirmacaoSenha) {
        exigirLogado(logado);
        if (novo.getPerfil() == Perfil.ATENDENTE && logado.getPerfil() != Perfil.ADMINISTRADOR) {
            throw new IllegalStateException("Somente o Administrador pode cadastrar atendentes.");
        }
        validarUsuario(novo.getNomeCompleto(), novo.getNomeUsuario(), novo.getEmail(),
                novo.getSenha(), confirmacaoSenha, novo.getPerfil(), novo.getCliente());
        validarNomeUnico(novo.getNomeUsuario(), null);

        usuarioRepo.incluirUsuario(novo);
    }

    public void editarUsuario(Usuario logado, Usuario usuario, String nomeCompleto,
            String nomeUsuario, String email, String senha, String confirmacaoSenha,
            Perfil perfil, Cliente cliente) {

        exigirAdministrador(logado);
        exigirSelecionado(usuario);
        validarUsuario(nomeCompleto, nomeUsuario, email, senha, confirmacaoSenha, perfil, cliente);
        validarNomeUnico(nomeUsuario, usuario);  

        if (usuario.getPerfil() == Perfil.ADMINISTRADOR && perfil != Perfil.ADMINISTRADOR) {
            throw new IllegalStateException("Não é possível alterar o perfil do Administrador.");
        }

        usuario.setNomeCompleto(nomeCompleto.trim());
        usuario.setNomeUsuario(nomeUsuario.trim());
        usuario.setEmail(email.trim());
        usuario.setSenha(senha);
        usuario.setPerfil(perfil);
        usuario.setCliente(cliente);

        usuarioRepo.editarUsuario(usuario);
    }

    public void habilitarUsuario(Usuario logado, Usuario alvo) {
        exigirAdministrador(logado);
        exigirSelecionado(alvo);
        alvo.habilitarStatus();
        usuarioRepo.editarUsuario(alvo);
    }

    public void desabilitarUsuario(Usuario logado, Usuario alvo) {
        exigirAdministrador(logado);
        exigirSelecionado(alvo);
        if (alvo.getPerfil() == Perfil.ADMINISTRADOR) {
            throw new IllegalStateException("O Administrador não pode ser desabilitado.");
        }
        alvo.desabilitarStatus();
        usuarioRepo.editarUsuario(alvo);
    }

    public void excluirUsuario(Usuario logado, Usuario alvo) {
        exigirAdministrador(logado);
        exigirSelecionado(alvo);
        if (alvo.getPerfil() == Perfil.ADMINISTRADOR) {
            throw new IllegalStateException("O Administrador não pode ser excluído.");
        }
        usuarioRepo.excluirUsuario(alvo);
    }

    public List<Usuario> listarUsuario() {
        return usuarioRepo.listarUsuario();
    }
}