
package repository;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Usuario;


public class UsuarioRepositoryMemoria implements IUsuarioRepository {
    
    
    private List<Usuario> listaUsuarios = new ArrayList<>();
    
    private int proximoId = 1;

    @Override
    public List<Usuario> listarUsuario() {
        List<Usuario> copialistaUsuarios = new ArrayList<>();
        
        for(Usuario usuario: listaUsuarios){
            copialistaUsuarios.add(usuario);
        }
        
        return copialistaUsuarios;
        
    }

    @Override
    public void incluirUsuario(Usuario usuario) {
        usuario.setId(proximoId);
        proximoId++;
        listaUsuarios.add(usuario);
    }

    @Override
    public void editarUsuario(Usuario usuario) {
    // Em memória, o objeto já foi alterado diretamente,
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        listaUsuarios.remove(usuario);
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        for(Usuario usuario : listaUsuarios ){
            if(usuario.getId() == id){
                return usuario;
            }
        }
        
        return null;
    }

    @Override
    public Usuario buscarNomeUsuario(String nome) {
        
        for(Usuario usuario : listaUsuarios ){
                    if(usuario.getNomeUsuario().equalsIgnoreCase(nome)){
                        return usuario;
                    }
                }

                return null;   
    }

    @Override
    public Usuario buscarLoginUsuario(String nome) {
        for(Usuario usuario : listaUsuarios ){
                    if(usuario.getNomeUsuario().equalsIgnoreCase(nome) || usuario.getEmail().equalsIgnoreCase(nome)  ){
                        return usuario;
                    }
                }

        return null; 
    }
    
   

    @Override
    public boolean existeUsuarioComCliente(Cliente cliente) {
        for(Usuario usuario : listaUsuarios){
                   if(usuario.getCliente() == cliente){
                       return true;

                   }

               }
          return false;    
    }
    
}
