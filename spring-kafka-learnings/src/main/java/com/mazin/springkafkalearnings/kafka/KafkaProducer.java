package com.mazin.springkafkalearnings.kafka;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
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
		Message<User> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, topicMazinJson).build();
		CompletableFuture<SendResult<String, String>> temp = kafkaTemplate.send(message);
		try {
			SendResult<String, String> sendResult = temp.get(10, TimeUnit.SECONDS);
			LOGGER.info(String.format("Message sent: %s", sendResult.toString()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
