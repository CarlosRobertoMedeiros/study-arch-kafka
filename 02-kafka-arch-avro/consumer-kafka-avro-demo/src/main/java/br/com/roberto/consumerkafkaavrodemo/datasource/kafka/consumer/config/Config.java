package br.com.roberto.consumerkafkaavrodemo.datasource.kafka.consumer.config;

import br.com.roberto.avro.schema.StockHistoryAvro;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    @Bean
    public ConsumerFactory<String, StockHistoryAvro> consumerFactory(KafkaProperties kafkaProperties) {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
        //return new DefaultKafkaConsumerFactory<>(this.consumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, StockHistoryAvro>> kafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<String, StockHistoryAvro> factory = new ConcurrentKafkaListenerContainerFactory<String, StockHistoryAvro>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));
        return factory;
    }

//    @Bean
//    public Map<String, Object> consumerConfigs() {
//
//        Map<String, Object> props = new HashMap<>();
//        //props.put(ConsumerConfig., 50);
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 50);
//
//        return props;
//    }
}
