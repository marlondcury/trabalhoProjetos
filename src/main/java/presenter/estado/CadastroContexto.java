package presenter.estado;

public interface CadastroContexto {
    void mudarEstado(CadastroEstado estado);
    void habilitarEdicao(boolean editar);
    void habilitarBotoes(boolean novo, boolean editar, boolean excluir, boolean salvar, boolean cancelar);
    void limparCampos();
    void exibirSelecionado();
    boolean temSelecionado();
    void incluir();
    void salvarEdicao();
    void excluirSelecionado();
}