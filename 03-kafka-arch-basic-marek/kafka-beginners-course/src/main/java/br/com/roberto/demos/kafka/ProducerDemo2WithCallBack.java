package br.com.roberto.demos.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.logging.Level;

public class ProducerDemo2WithCallBack {
    private static final Logger logger = LoggerFactory.getLogger(ProducerDemo2WithCallBack.class.getSimpleName());
    public static void main(String[] args) throws InterruptedException {
        logger.info("I am a Kafka Producer !");

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //create the producer
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        for (int i=0; i<10;i++) {
            //create and producer record
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_java","hello world "+ i);
            //send the data - asynchronous
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    //executes every time a record is successfully sent or an exception is thrown
                    if (e == null) {
                        logger.info("Received new metadata/ \n" +
                                "Topic: " + metadata.topic() + "\n" +
                                "Partition: " + metadata.partition() + "\n" +
                                "Offset: " + metadata.offset() + "\n" +
                                "Timestamp: " + metadata.timestamp());
                    } else {
                        logger.error("Error while producing", e);
                    }
                    ;

                }
            });


                Thread.sleep(1000);


        }

        // flush data - syncronous
        producer.flush();

        // flush and close producer
        producer.close();

    }
}
