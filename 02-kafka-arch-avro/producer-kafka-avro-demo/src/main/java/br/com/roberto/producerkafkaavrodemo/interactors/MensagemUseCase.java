package br.com.roberto.producerkafkaavrodemo.interactors;

import br.com.roberto.producerkafkaavrodemo.repositories.StockHistoryRepository;
import br.com.roberto.producerkafkaavrodemo.transportlayers.http.dto.StockHistoryRequest;
import br.com.roberto.producerkafkaavrodemo.transportlayers.http.mapper.StockHistoryMapper;
import org.springframework.stereotype.Service;

@Service
public class MensagemUseCase {

    private final StockHistoryRepository stockHistoryRepository;
    //private final SpringAvroProducer springAvroProducer;

    public MensagemUseCase(StockHistoryRepository stockHistoryRepository) {
        this.stockHistoryRepository = stockHistoryRepository;
    }
    public void enviarDadosDeHistoricoDoEstoque(StockHistoryRequest stockHistoryRequest) {
        var stockHistoryEntity = StockHistoryMapper.INSTANCE.dtoRequestToEntity(stockHistoryRequest);
        //StockHistory stockHistory = StockHistory.newBuilder().build();

        this.stockHistoryRepository.enviarDadosDeHistoricoDoEstoque(stockHistoryEntity);
    }


}
