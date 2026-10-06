package presenter.estado;

public class EdicaoEstado extends CadastroEstado {

    public EdicaoEstado(CadastroContexto presenter) {
        super(presenter);
    }

    @Override
    public void entrar() {
        presenter.habilitarEdicao(true);
        presenter.habilitarBotoes(false, false, false, true, true);
    }

    @Override
    public void salvar() {
        presenter.salvarEdicao();
        presenter.mudarEstado(new VisualizacaoEstado(presenter));
    }

    @Override
    public void cancelar() {
        presenter.mudarEstado(new VisualizacaoEstado(presenter));
    }
}