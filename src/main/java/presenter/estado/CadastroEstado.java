package presenter.estado;

public abstract class CadastroEstado {

    protected final CadastroContexto presenter;

    public CadastroEstado(CadastroContexto presenter) {
        this.presenter = presenter;
    }

    public abstract void entrar();

    public void novo()     { negar(); }
    public void editar()   { negar(); }
    public void excluir()  { negar(); }
    public void salvar()   { negar(); }
    public void cancelar() { negar(); }
    public void selecionouLinha() { }

    private void negar() {
        throw new IllegalStateException("Operação não permitida neste modo.");
    }
}