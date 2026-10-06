
package repository;

import java.util.List;
import model.Cliente;


public interface IClienteRepository {
    
    public List<Cliente> listarCliente();
    
    public void incluirCliente(Cliente cliente);
    
    public void editarCliente(Cliente cliente);
    
    public void excluirCliente(Cliente cliente);
    
    public Cliente buscarClientePorId(int id);
    
}
