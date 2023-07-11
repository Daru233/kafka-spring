package com.daru.kafkaspring;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import java.util.concurrent.TimeUnit;


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class EmbeddedKafkaIntegrationTest {

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Before
    public void sendData(){

    }

    @Test
    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
            throws Exception {
        String data = "Sending with our own simple KafkaProducer";
        String topic = "Test_Topic";

        long start = System.currentTimeMillis();

        kafkaTemplate.send(topic, data);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("ASDF" + timeElapsed);

        Assertions.assertTrue(messageConsumed);
        Assertions.assertEquals(consumer.getPayload(), data);
    }


}