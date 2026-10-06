
package service;

import model.Usuario;
import model.StatusUsuario;
import repository.IUsuarioRepository;


public class AutenticacaoService {
    
  
    private final IUsuarioRepository usuarioRepo;
    
    public AutenticacaoService(IUsuarioRepository usuarioRepo){
        this.usuarioRepo = usuarioRepo;
    }

    
    public Usuario autenticarUsuario(String login, String senha){
        
        Usuario usuario = usuarioRepo.buscarLoginUsuario(login);

        if(login == null || login.isBlank() || senha == null || senha.isBlank()){
            throw new IllegalArgumentException("Informe o usuário ou e-mail e a senha.");
        }
        if(usuario == null){
            throw new IllegalArgumentException("Credenciais inválidas.");
        }
        
        
        if(!usuario.getSenha().equals(senha)){
            
            throw new IllegalArgumentException("Credenciais inválidas.");

        }
        if(usuario.getStatusUsuario() == StatusUsuario.DESABILITADO){
            
            throw new IllegalStateException("Usuário desabilitado.");

        }
        
        
        return usuario;
       
    
    }
    
}
