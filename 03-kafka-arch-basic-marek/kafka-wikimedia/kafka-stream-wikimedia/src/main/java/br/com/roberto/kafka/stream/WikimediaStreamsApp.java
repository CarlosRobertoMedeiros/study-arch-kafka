package br.com.roberto.kafka.stream;

import br.com.roberto.kafka.stream.processor.BotCountStreamBuilder;
import br.com.roberto.kafka.stream.processor.EventCountTimeseriesBuilder;
import br.com.roberto.kafka.stream.processor.WebsiteCountStreamBuilder;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class WikimediaStreamsApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaStreamsApp.class);
    private static final Properties properties;
    private static final String INPUT_TOPIC = "wikimedia.recentchange";

    static {
        properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "wikimedia-stats-application");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    }

    public static void main(String[] args) {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> changeJsonStream = streamsBuilder.stream(INPUT_TOPIC);

        BotCountStreamBuilder botCountStreamBuilder = new BotCountStreamBuilder(changeJsonStream);
        botCountStreamBuilder.setup();

        WebsiteCountStreamBuilder webSiteCountStreamBuilder = new WebsiteCountStreamBuilder(changeJsonStream);
        webSiteCountStreamBuilder.setup();

        EventCountTimeseriesBuilder eventCountTimeseriesBuilder = new EventCountTimeseriesBuilder(changeJsonStream);
        eventCountTimeseriesBuilder.setup();

        final Topology appTopology = streamsBuilder.build();
        LOGGER.info("Topology: {}", appTopology.describe());
        KafkaStreams kafkaStreams = new KafkaStreams(appTopology, properties);
        kafkaStreams.start();
    }


}
