package br.com.roberto.consumerkafkaavrodemo.datasource.kafka.consumer;

import br.com.roberto.avro.schema.StockHistoryAvro;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


@EnableKafka
@Component
public class MensagemKafkaConsumerWithAvro {

    private static Logger logger = Logger.getLogger(MensagemKafkaConsumerWithAvro.class.getName());

//    @KafkaListener(topics = "${avro.topic.name}",
//            containerFactory = "kafkaListenerContainerFactory")
    @KafkaListener(topics = "${avro.topic.name}",
                   topicPartitions = @TopicPartition(topic = "${avro.topic.name}",
                   partitions = "#{@finder.partitions('${avro.topic.name}')}"),
                   containerFactory = "kafkaListenerContainerFactory")
    public void read(ConsumerRecord<String, StockHistoryAvro> record){
        String key=record.key();
        StockHistoryAvro history=record.value();
        logger.log(Level.INFO,"Message received for key ".concat(key).concat("value ").concat(history.toString()));
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("${avro.topic.name}", 10, (short) 0);
    }

    @Bean
    public PartitionFinder finder(ConsumerFactory<String, String> consumerFactory) {
        return new PartitionFinder(consumerFactory);
    }

    public static class PartitionFinder {

        public PartitionFinder(ConsumerFactory<String, String> consumerFactory) {
            this.consumerFactory = consumerFactory;
        }

        private final ConsumerFactory<String, String> consumerFactory;

        public String[] partitions(String topic) {
            try (Consumer<String, String> consumer = consumerFactory.createConsumer()) {
                return consumer.partitionsFor(topic).stream()
                        .map(pi -> "" + pi.partition())
                        .toArray(String[]::new);
            }
        }

    }


}
