package presenter.estado;

public class VisualizacaoEstado extends CadastroEstado {

    public VisualizacaoEstado(CadastroContexto presenter) {
        super(presenter);
    }

    @Override
    public void entrar() {
        presenter.habilitarEdicao(false);
        presenter.exibirSelecionado();
        atualizarBotoes();
    }

    @Override
    public void novo() {
        presenter.mudarEstado(new InclusaoEstado(presenter));
    }

    @Override
    public void editar() {
        if (!presenter.temSelecionado()) {
            throw new IllegalArgumentException("Selecione um registro para editar.");
        }
        presenter.mudarEstado(new EdicaoEstado(presenter));
    }

    @Override
    public void excluir() {
        presenter.excluirSelecionado();
        entrar();
    }

    @Override
    public void selecionouLinha() {
        presenter.exibirSelecionado();
        atualizarBotoes();
    }

    private void atualizarBotoes() {
        boolean sel = presenter.temSelecionado();
        presenter.habilitarBotoes(true, sel, sel, false, false);
    }
}