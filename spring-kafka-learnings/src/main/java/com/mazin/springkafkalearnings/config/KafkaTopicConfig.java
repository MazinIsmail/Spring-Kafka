package com.mazin.springkafkalearnings.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Value("${spring.kafka.topic.mazin-json}")
	private String topicMazinJson;

	@Bean
	public NewTopic mazinJsonTopic() {
		return TopicBuilder.name(topicMazinJson).build();
	}
}
