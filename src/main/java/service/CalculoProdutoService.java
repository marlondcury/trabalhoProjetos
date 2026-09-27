
package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import model.HistoricoPreco;
import model.Produto;
import repository.IHistoricoPrecoRepository;
import repository.IProdutoRepository;


public class CalculoProdutoService {
        private IProdutoRepository produtoRepo;
        private   IHistoricoPrecoRepository historicoRepo;
        private static final int INTERVALO_MINIMO_DIAS = 10;
        

    public CalculoProdutoService(IProdutoRepository produtoRepo, IHistoricoPrecoRepository historicoRepo) {
        this.produtoRepo = produtoRepo;
        this.historicoRepo = historicoRepo;
    }
        

    private double arredondar(double valor) {
        return Math.round(valor * 100) / 100.0;
    }

    public List<Produto> calcular(LocalDate dataCalculo) {


        if (dataCalculo == null) {
            throw new IllegalArgumentException("A data do cálculo é obrigatória.");
        }

        LocalDate ultimaData = historicoRepo.exibirUltimaData();
        if (ultimaData != null
                && ChronoUnit.DAYS.between(ultimaData, dataCalculo) < INTERVALO_MINIMO_DIAS) {
            throw new IllegalStateException(
                    "O cálculo só pode ser realizado novamente após " + INTERVALO_MINIMO_DIAS + " dias.");
        }


        List<Produto> produtos = produtoRepo.exibirProdutos();

        for (Produto produto : produtos) {

            double percentual = produto.getCategoria().getPercentualLucro();

            double precoVenda = arredondar(produto.getPrecoCusto() * (1 + percentual / 100));

            produto.atualizarPrecoMargemAtual(precoVenda, percentual);
            produtoRepo.editarProduto(produto);

            HistoricoPreco historico = new HistoricoPreco(dataCalculo, percentual, precoVenda, produto);
            historicoRepo.incluirHistorico(historico);
        }

        return produtos;
    }

    
}
