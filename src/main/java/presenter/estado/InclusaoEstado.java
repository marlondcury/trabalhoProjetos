package presenter.estado;

public class InclusaoEstado extends CadastroEstado {

    public InclusaoEstado(CadastroContexto presenter) {
        super(presenter);
    }

    @Override
    public void entrar() {
        presenter.limparCampos();
        presenter.habilitarEdicao(true);
        presenter.habilitarBotoes(false, false, false, true, true);
    }

    @Override
    public void salvar() {
        presenter.incluir();
        presenter.mudarEstado(new VisualizacaoEstado(presenter));
    }

    @Override
    public void cancelar() {
        presenter.mudarEstado(new VisualizacaoEstado(presenter));
    }
}