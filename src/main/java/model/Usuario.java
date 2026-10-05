
package model;


public class Usuario {
    
    private int id;
    private String nomeCompleto;
    private String nomeUsuario;
    private String email;
    private String senha;
    private Perfil perfil;
    private StatusUsuario statusUsuario;
    private Cliente cliente;

    public Usuario( String nomeCompleto,String nomeUsuario, String email, String senha, Perfil perfil, Cliente cliente) {
        this.nomeCompleto = nomeCompleto;
        this.nomeUsuario =  nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.statusUsuario = StatusUsuario.HABILITADO;
        this.cliente = cliente;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getEmail() {
        return email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public String getSenha() {
        return senha;
    }

    public StatusUsuario getStatusUsuario() {
        return statusUsuario;
    }

    public int getId() {
        return id;
    }

   
    
    public void habilitarStatus(){
        this.statusUsuario = StatusUsuario.HABILITADO;
    }
    
     public void desabilitarStatus(){
        this.statusUsuario = StatusUsuario.DESABILITADO;
    }
    
    
    
}
