
package repository;

import java.util.List;
import model.Usuario;

public interface IUsuarioRepository {
    
    
    public List<Usuario> listarUsuario();
    
    public void incluirUsuario(Usuario usuario);
    
    public void editarUsuario(Usuario usuario);
    
    public void excluirUsuario(Usuario usuario);
    
    public Usuario buscarUsuarioPorId(int id);
    
    public Usuario buscarNomeUsuario(String nome);
    public Usuario buscarLoginUsuario(String nome);

    
    
    
}
