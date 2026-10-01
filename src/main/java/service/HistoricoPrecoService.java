package service;

import java.util.Comparator;
import java.util.List;
import model.HistoricoPreco;
import model.Produto;
import repository.IHistoricoPrecoRepository;

public class HistoricoPrecoService {

    private final IHistoricoPrecoRepository historicoRepo;

    public HistoricoPrecoService(IHistoricoPrecoRepository historicoRepo) {
        this.historicoRepo = historicoRepo;
    }

    public List<HistoricoPreco> listarPorProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Selecione um produto.");
        }

        List<HistoricoPreco> historicos = historicoRepo.exibirHistoricoProdutos(produto);
        historicos.sort(Comparator.comparing(HistoricoPreco::getData).reversed());
        return historicos;
    }
}