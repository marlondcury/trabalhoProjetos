
package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.HistoricoPreco;
import model.Produto;


public class HistoricoPrecoRepositoryMemoria implements IHistoricoPrecoRepository {
    
    private List<HistoricoPreco> listaHistorico = new ArrayList<>();

    
     public void incluirHistorico(HistoricoPreco historico) {
         listaHistorico.add(historico);
         
     }
    
    public List<HistoricoPreco> exibirHistoricoProdutos(Produto produto){
        
        List<HistoricoPreco> listaHistoricoCopia = new ArrayList<>();
        
        for(HistoricoPreco historico : listaHistorico){
            if(historico.getProduto() == produto){
                listaHistoricoCopia.add(historico);
            }
        }
        
        return listaHistoricoCopia;
    }
    
    public LocalDate exibirUltimaData(){
        LocalDate ultimaData = null;
    
        for(HistoricoPreco historicoData : listaHistorico){
            if(ultimaData == null || historicoData.getData().isAfter(ultimaData)){
                ultimaData = historicoData.getData();
            }
                
        }
        return ultimaData;

    }
    
}
