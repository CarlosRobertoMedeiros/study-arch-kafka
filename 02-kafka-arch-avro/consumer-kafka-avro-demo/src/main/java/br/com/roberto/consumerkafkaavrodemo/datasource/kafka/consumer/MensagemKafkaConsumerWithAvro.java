package br.com.roberto.consumerkafkaavrodemo.datasource.kafka.consumer;

import br.com.roberto.avro.schema.StockHistoryAvro;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


@EnableKafka
@Component
public class MensagemKafkaConsumerWithAvro {

    private static Logger logger = Logger.getLogger(MensagemKafkaConsumerWithAvro.class.getName());

    @KafkaListener(topics = "${avro.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void read(ConsumerRecord<String, StockHistoryAvro> record){
        String key=record.key();
        StockHistoryAvro history=record.value();
        logger.log(Level.INFO,"Message received for key ".concat(key).concat("value ").concat(history.toString()));
    }

}
