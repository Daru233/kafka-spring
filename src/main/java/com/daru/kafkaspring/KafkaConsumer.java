package com.daru.kafkaspring;

import com.daru.kafkaspring.Message.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumer {

    // some comment

    // another comment

    // a third commen:x:s

    private CountDownLatch latch = new CountDownLatch(1);
    private Message payload;

    @KafkaListener(topics = "message_topic", groupId = "groupID")
    void listener(Message data) {
        latch.countDown();
        payload = data;
        System.out.println("Received: " + data.toString());
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public Message getPayload() {
        return payload;
    }
}
