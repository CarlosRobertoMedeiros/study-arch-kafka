package br.com.roberto.producerkafkaavrodemo.datasource.kafka.producer;

import br.com.roberto.producerkafkaavrodemo.entities.StockHistoryEntity;
import br.com.roberto.producerkafkaavrodemo.repositories.StockHistoryRepository;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MensagemKafkaProducerWithAvro implements StockHistoryRepository {

    private static Logger logger = Logger.getLogger(MensagemKafkaProducerWithAvro.class.getName());
    private final KafkaTemplate<String, StockHistoryEntity> kafkaAlunoTemplate;

    public MensagemKafkaProducerWithAvro(KafkaTemplate<String, StockHistoryEntity> kafkaAlunoTemplate) {

        this.kafkaAlunoTemplate = kafkaAlunoTemplate;
    }


    @Override
    public void enviarDadosDeHistoricoDoEstoque(StockHistoryEntity stockHistoryEntity) {
        ListenableFuture future = this.kafkaAlunoTemplate.send("TOPIC_MENSAGEM_ENVIO_STOCK", stockHistoryEntity);

        future.addCallback(new KafkaSendCallback() {
            @Override
            public void onFailure(KafkaProducerException e) {
                logger.log(Level.WARNING,"Error Message not sent to Topic "+ e.getMessage());
            }

            @Override
            public void onSuccess(Object result) {
                logger.log(Level.INFO,"Message send to Topic "+ result.toString());
            }
        });


    }
}