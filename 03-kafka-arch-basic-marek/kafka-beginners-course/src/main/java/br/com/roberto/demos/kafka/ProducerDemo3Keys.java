package br.com.roberto.demos.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerDemo3Keys {
    private static final Logger logger = Logger.getLogger(ProducerDemo3Keys.class.getSimpleName());
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

            String topic = "demo_java";
            String value = "hello world "+i;
            String key = "id_"+i;


            //create and producer record
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
            //send the data - asynchronous
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    //executes every time a record is successfully sent or an exception is thrown
                    if (e == null) {
                        logger.info("Received new metadata/ \n" +
                                "Topic: " + metadata.topic() + "\n" +
                                "Key: " + producerRecord.key() + "\n" +
                                "Partition: " + metadata.partition() + "\n" +
                                "Offset: " + metadata.offset() + "\n" +
                                "Timestamp: " + metadata.timestamp());
                    } else {
                        logger.log(Level.SEVERE, "Error while producing", e);
                    }
                    ;

                }
            });


                //Thread.sleep(1000);


        }

        // flush data - syncronous
        producer.flush();

        // flush and close producer
        producer.close();

    }
}
