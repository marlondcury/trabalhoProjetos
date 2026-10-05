
package model;

public enum StatusUsuario {
    
    HABILITADO("Habilitado"),
    DESABILITADO("Desabilitado");
    
    private final String tipoStatusUsuario;
    
    StatusUsuario(String tipoStatusUsuario){
        this.tipoStatusUsuario = tipoStatusUsuario;
    
    }
    
    @Override
    public String toString(){
    
        return tipoStatusUsuario;
    }
}
