package com.project.task;

import java.util.TreeMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import com.project.task.model.Message;

import lombok.extern.apachecommons.CommonsLog;

@Configuration
@EnableKafka
@CommonsLog
public class KafkaTopicListener {

	private TreeMap<String, Message> messagesById_;
	private TreeMap<Long, Message> messagesByReceivedTS_;
	
	@Bean
	public TreeMap<String, Message> messagesById() {
		messagesById_ = new TreeMap<String, Message>();
		return messagesById_;
	}
	
	@Bean
	public TreeMap<Long, Message> messagseByReceivedTS() {
		messagesByReceivedTS_ = new TreeMap<Long, Message>();
		return messagesByReceivedTS_;
	}
	
	@KafkaListener(topics = "${bcentral.application.topic.name}")
	public void messageListener(@Payload Message message, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
		log.info("Message received: " + message +" on timestamp: " + ts);
		messagesByReceivedTS_.put(ts, message);
		messagesById_.put(message.getId(), message);
	}
}
