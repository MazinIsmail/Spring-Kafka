package com.mazin.springkafkalearnings.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazin.springkafkalearnings.kafka.KafkaProducer;
import com.mazin.springkafkalearnings.payload.User;

@RestController
@RequestMapping("/springkafka")
public class SampleController {

	private KafkaProducer kafkaProducer;

	public SampleController(KafkaProducer KafkaProducer) {
		this.kafkaProducer = KafkaProducer;
	}
	
	// http:localhost:8080/springkafka/publish
	@PostMapping("/publish")
	public ResponseEntity<String> publish(@RequestBody User user) {
		kafkaProducer.sendMessage(user);
		return ResponseEntity.ok("Json message sent to kafka topic");
	}
}
