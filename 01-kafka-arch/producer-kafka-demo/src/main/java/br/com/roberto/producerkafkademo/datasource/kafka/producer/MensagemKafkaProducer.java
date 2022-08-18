package br.com.roberto.producerkafkademo.datasource.kafka.producer;

import br.com.roberto.producerkafkademo.entities.AlunoEntity;
import br.com.roberto.producerkafkademo.entities.MensagemEntity;
import br.com.roberto.producerkafkademo.repositories.AlunoRepository;
import br.com.roberto.producerkafkademo.repositories.MensagemRepository;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MensagemKafkaProducer implements MensagemRepository, AlunoRepository {
    private final KafkaTemplate<String, String> kafkaMensagemStringTemplate;
    private final KafkaTemplate<String, AlunoEntity> kafkaAlunoTemplate;

    private static Logger logger = Logger.getLogger(MensagemKafkaProducer.class.getName());

    public MensagemKafkaProducer(KafkaTemplate<String, String> kafkaMensagemStringTemplate, KafkaTemplate<String, AlunoEntity> kafkaAlunoTemplate) {
        this.kafkaMensagemStringTemplate = kafkaMensagemStringTemplate;
        this.kafkaAlunoTemplate = kafkaAlunoTemplate;
    }

    @Override
    public void enviarDadosDoAluno(AlunoEntity aluno) {
        ListenableFuture future = this.kafkaAlunoTemplate.send("TOPIC_MENSAGEM_ENVIO_ALUNO",aluno);

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

    @Override
    public void enviarMensagem(MensagemEntity mensagem) {
        ListenableFuture future = this.kafkaMensagemStringTemplate.send("TOPIC_MENSAGEM_ENVIO",mensagem.getMensagem());

        future.addCallback(new KafkaSendCallback() {

            @Override
            public void onFailure(KafkaProducerException e) {
                logger.log(Level.WARNING,"Error Message not sent to Topic "+ e.getMessage());
            }

            @Override
            public void onSuccess(Object result) {
                logger.log(Level.INFO,"Message sent to Topic "+ result.toString());
            }
        });
    }
}
