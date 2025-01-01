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

@Configuration
@EnableKafka
public class KafkaTopicListener {

	private TreeMap<String, Message> messagesById_;
	private TreeMap<Long, Message> messageByReceivedTS_;
	
	@Bean
	public TreeMap<String, Message> messagesById() {
		messagesById_ = new TreeMap<String, Message>();
		return messagesById_;
	}
	
	@Bean
	public TreeMap<Long, Message> messageByReceivedTS() {
		messageByReceivedTS_ = new TreeMap<Long, Message>();
		return messageByReceivedTS_;
	}
	
	@KafkaListener(topics = "${bcentral.application.topic.name}")
	public void messageListener(@Payload Message message, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
		messageByReceivedTS_.put(ts, message);
		messagesById_.put(message.getId(), message);
		System.out.println("Message Received: " + message);
		System.out.println("TreeMap size: " + messagesById_.size());
	}
}
