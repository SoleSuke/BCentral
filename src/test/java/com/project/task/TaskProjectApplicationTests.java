package com.project.task;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.kafka.*;

import com.project.task.model.Message;
import com.project.task.model.Metadata;
import com.project.task.TaskProjectApplication;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class TaskProjectApplicationJUnitTest {
	
	@Autowired
	private KafkaConsumer consumer;
	
	@Autowired
	private KafkaProducer producer;
	private Metadata<String,String> metada;
	
	metadata<source,type> = <{"localhost","test1"},{"localhost","test2"},{"localhost","test3"},{"localhost","test4"}>;
	
	@Test
    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived() 
    	throws Exception {
		String message = "Sending with our own simple KafkaProducer";
    	        
		producer.send(message.metadata.type, message);
    	        
		boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
		assertTrue(messageConsumed);
		assertThat(consumer.getPayload(), containsString(data));
	}	
	

}
