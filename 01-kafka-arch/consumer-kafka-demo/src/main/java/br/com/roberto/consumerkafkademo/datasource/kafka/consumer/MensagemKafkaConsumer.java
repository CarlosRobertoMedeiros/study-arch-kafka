package br.com.roberto.consumerkafkademo.datasource.kafka.consumer;

import br.com.roberto.consumerkafkademo.entities.AlunoEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MensagemKafkaConsumer {

    private static Logger logger = Logger.getLogger(MensagemKafkaConsumer.class.getName());

    @KafkaListener(topics = "${topic.consumer.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(AlunoEntity alunoEntity){
        logger.log(Level.INFO ,"Mensagem Recebida ::::" + alunoEntity);
    }
}
