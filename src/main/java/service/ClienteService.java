
package service;

import java.util.List;
import model.Cliente;
import repository.IClienteRepository;
import repository.IUsuarioRepository;


public class ClienteService {
    
    private final IUsuarioRepository usuarioRepo;
    private final IClienteRepository clienteRepo;

    public ClienteService(IUsuarioRepository usuarioRepo, IClienteRepository clienteRepo) {
        this.usuarioRepo = usuarioRepo;
        this.clienteRepo = clienteRepo;
    }
    
   private void validarCliente(String nome, String logradouro, String bairro, String cidade, String uf){
       if(nome == null || nome.isBlank()){
                   throw new IllegalArgumentException("O nome não pode ser vazio.");

       }
       if(logradouro == null || logradouro.isBlank()){
                   throw new IllegalArgumentException("O logradouro não pode ser vazio.");

       }
       if(bairro == null || bairro.isBlank()){
                   throw new IllegalArgumentException("O bairro não pode ser vazio.");

       }
       if(cidade == null || cidade.isBlank()){
                   throw new IllegalArgumentException("A cidade não pode ser vazia.");

       }
       if(uf == null || uf.isBlank()){
                   throw new IllegalArgumentException("O estado não pode ser vazio.");

       }
       
       if(uf.trim().length() != 2){
                   throw new IllegalArgumentException("O estado deve ter 2 caracteres.");

       }
       

   }
   
   public void incluirCliente(Cliente cliente){
       validarCliente(cliente.getNomeCliente(), cliente.getLogradouro(), 
               cliente.getBairro(), cliente.getCidade(), cliente.getUf());
       
       cliente.setUf(cliente.getUf().trim().toUpperCase());
       
       clienteRepo.incluirCliente(cliente);   
   
   }
   
    public void editarCliente(Cliente cliente, String novoNome, String novoLogradouro, 
        String novoBairro, String novaCidade, String novoUf){
        
        validarCliente(novoNome,novoLogradouro, novoBairro, novaCidade, novoUf);
        
        cliente.setNomeCliente(novoNome);

        cliente.setBairro(novoBairro);
        cliente.setCidade(novaCidade);
        cliente.setLogradouro(novoLogradouro);
        cliente.setUf(novoUf.trim().toUpperCase());
        
        clienteRepo.editarCliente(cliente);
    
    }
    
    public void excluirCliente(Cliente cliente){
        if(cliente == null){
           
          throw new IllegalArgumentException("Selecione um cliente para excluir.");

        
        }
        
        if(usuarioRepo.existeUsuarioComCliente(cliente)){
                    throw new IllegalStateException("Não é possível excluir: existem usuarios associados a este cliente.");

        }
        
        clienteRepo.excluirCliente(cliente);
    }

    public List<Cliente> listarCliente(){
        return clienteRepo.listarCliente();
    }

    
    
}
