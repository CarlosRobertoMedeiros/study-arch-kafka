package br.com.roberto.demos.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

public class ConsumerDemo3With {
    private static final Logger logger = Logger.getLogger(ConsumerDemo3With.class.getSimpleName());
    public static void main(String[] args)  {
        logger.info("I am a Kafka Consumer !");

        String bootstrapServer = "127.0.0.1:9092";
        String groupId = "my-third-app";
        String topic ="demo_java";

        //create consumer config
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");//none,earliest,latest

        //create consumer
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);

        //get reference to the current thread
        final Thread mainThread = Thread.currentThread();

        //Adding the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(){

            @Override
            public void run(){
                logger.info("Detected a shutdown, let´s exit by calling consumer .. wakeup()....");
                consumer.wakeup();

                //join the main thread to allow the execution of the code in the main thread
                try {
                    mainThread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        try {
            // subscribe consumer to our topic(s)
            consumer.subscribe(Arrays.asList(topic));

            // poll for new data
            while (true) {

                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : records) {
                    logger.info(" Key: " + record.key()
                            + " Value: " + record.value()
                            + " Partition: " + record.partition()
                            + " OffSet: " + record.offset());
                }


            }
        }catch (WakeupException e){
            logger.info("Wake up Exception !!");
            //We ignore this as this is an expected exception when closing a consumer
        }catch (Exception e){
            logger.severe("Unexpected Exception");
        }finally {
            consumer.close();  //this will also commit the offsets if need be
            logger.info("The consumer is now gracefully closed !!");
        }




    }
}
