package br.com.roberto.producerkafkaavrodemo.datasource.kafka.producer;

import br.com.roberto.avro.schema.StockHistoryAvro;
import br.com.roberto.producerkafkaavrodemo.entities.StockHistoryEntity;
import br.com.roberto.producerkafkaavrodemo.repositories.StockHistoryRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MensagemKafkaProducerWithAvro implements StockHistoryRepository {

    private static Logger logger = Logger.getLogger(MensagemKafkaProducerWithAvro.class.getName());
    private final KafkaTemplate<String, StockHistoryAvro> kafkaStockHistoryTemplate;

    public MensagemKafkaProducerWithAvro(KafkaTemplate<String, StockHistoryAvro> kafkaStockHistoryTemplate) {

        this.kafkaStockHistoryTemplate = kafkaStockHistoryTemplate;
    }


    @Override
    public void enviarDadosDeHistoricoDoEstoque(StockHistoryEntity stockHistoryEntity) {

        StockHistoryAvro stockHistoryAvro = converteEntidadeParaBinaryAvro(stockHistoryEntity);

        ListenableFuture<SendResult<String,StockHistoryAvro>> future = this.kafkaStockHistoryTemplate.send("Topic_Mensagem_Envio_Stock", stockHistoryAvro);

        future.addCallback(new ListenableFutureCallback<SendResult<String, StockHistoryAvro>>(){

            @Override
            public void onSuccess(SendResult<String, StockHistoryAvro> result) {
                logger.log(Level.INFO,"Message send to Topic ".concat(result.toString()));
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.log(Level.WARNING,"Error Message not sent to Topic ".concat(ex.getMessage()));
            }
        });
    }

    private StockHistoryAvro converteEntidadeParaBinaryAvro(StockHistoryEntity stockHistoryEntity) {
        StockHistoryAvro stockHistoryAvro = StockHistoryAvro.newBuilder().build();
        stockHistoryAvro.setTradeType(stockHistoryEntity.getTradeType());
        stockHistoryAvro.setAmount(stockHistoryEntity.getAmount());
        stockHistoryAvro.setPrice(stockHistoryEntity.getPrice());
        stockHistoryAvro.setStockName(stockHistoryEntity.getStockName());
        stockHistoryAvro.setTradeQuantity(stockHistoryEntity.getTradeQuantity());
        stockHistoryAvro.setTradeMarket(stockHistoryEntity.getTradeMarket());
        stockHistoryAvro.setTradeId(stockHistoryEntity.getTradeId());
        return stockHistoryAvro;
    }


}