package com.daru.kafkaspring;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringApplication.class, args);
	}

	// sending message to the topic
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
		return args -> {
//			for (int i = 0; i < 100; i++) {
//				kafkaTemplate.send("kafka_topic", "Hello Kafka " + i);
//			}
			kafkaTemplate.send("kafka_topic", "Sending with our own simple KafkaProducer");

		};
	}

	// if does NOT exist will create
	// if DOES exist will skip
	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("topic_created_from_sping")
				.partitions(1)
				.replicas(1)
				.build();
	}

}
