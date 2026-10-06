
package repository;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;


public class ClienteRepositoryMemoria implements IClienteRepository{
    
    
    private List<Cliente> listaClientes = new ArrayList<>();
    
    private int proximoId = 1;


    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> copiaListaClientes = new ArrayList<>();
        
        for(Cliente cliente : listaClientes){
            copiaListaClientes.add(cliente);
        }
        
        return copiaListaClientes;
    }

    @Override
    public void incluirCliente(Cliente cliente) {
        
        cliente.setId(proximoId);
        proximoId++;
        listaClientes.add(cliente);
        
        
    }

    @Override
    public void editarCliente(Cliente cliente) {
        
      // Em memória, o objeto já foi alterado diretamente,

    }

    @Override
    public void excluirCliente(Cliente cliente) {
        listaClientes.remove(cliente);
    }

    @Override
    public Cliente buscarClientePorId(int id) {
        for(Cliente cliente : listaClientes){
            if(cliente.getId() == id){
                return cliente;
            }
        
        }
        
        return null;
    }
    
}
