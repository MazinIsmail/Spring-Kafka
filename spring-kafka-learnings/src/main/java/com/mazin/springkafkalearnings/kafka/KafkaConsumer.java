package com.mazin.springkafkalearnings.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mazin.springkafkalearnings.payload.User;

@Service
public class KafkaConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "${spring.kafka.topic.mazin-json}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(User user) {
		LOGGER.info(String.format("Message recieved: %s", user.toString()));
	}
}
