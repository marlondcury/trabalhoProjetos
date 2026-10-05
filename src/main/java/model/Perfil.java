
package model;

public enum Perfil {
    
    ADMINISTRADOR("Administrador"),
    ATENDENTE("Atendente"),
    CLIENTE("Cliente");
    
    private final String tipoPerfil;
    
    Perfil(String tipoPerfil){
        this.tipoPerfil = tipoPerfil;
    
    }
    
    @Override
    public String toString(){
    
        return tipoPerfil;
    }
    
    
}
