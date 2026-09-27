
package repository;

import java.time.LocalDate;
import java.util.List;
import model.HistoricoPreco;
import model.Produto;


public interface IHistoricoPrecoRepository {
    
    
    public void incluirHistorico(HistoricoPreco historico );
    
    public List<HistoricoPreco> exibirHistoricoProdutos(Produto produto);
    
    public LocalDate exibirUltimaData();
}
