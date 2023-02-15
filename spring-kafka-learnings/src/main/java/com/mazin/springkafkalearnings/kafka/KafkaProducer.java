package com.mazin.springkafkalearnings.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.mazin.springkafkalearnings.payload.User;

@Service
public class KafkaProducer {

	@Value("${spring.kafka.topic.mazin-json}")
	private String topicMazinJson;

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

	private KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(User data) {
		LOGGER.info(String.format("Message sent -> %s", data.toString()));
		Message<User> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, topicMazinJson).build();
		kafkaTemplate.send(message);
	}
}
