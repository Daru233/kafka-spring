package com.daru.kafkaspring;

import com.daru.kafkaspring.Message.Message;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;

@SpringBootApplication
public class KafkaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringApplication.class, args);
	}

	@Value("${message.topic}")
	String TOPIC;

	public Message generateMessage() {
		return new Message("header1", "body1", Instant.now().getEpochSecond());
	}

	// sending message to the topic
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, Message> kafkaTemplate){
		return args -> {
			kafkaTemplate.send(TOPIC, generateMessage());
		};
	}

	// if does NOT exist will create
	// if DOES exist will skip
	@Bean
	public NewTopic topic() {
		return TopicBuilder.name(TOPIC)
				.partitions(1)
				.replicas(1)
				.build();
	}

}
