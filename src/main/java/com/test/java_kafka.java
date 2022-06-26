package com.test;


import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.test.MongoTest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import io.vertx.kafka.client.consumer.KafkaConsumerRecord;

public class java_kafka extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		this.setKafkaConsumer();
		System.out.println("Verticle started");
	}
	
	private void setKafkaConsumerWithPolling() {
		Map<String, String> config = new HashMap<>();
		config.put("bootstrap.servers", "localhost:9092");
		config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("group.id", "my_group");
		config.put("auto.offset.reset", "earliest");
		config.put("enable.auto.commit", "false");

		// use consumer for interacting with Apache Kafka
		
		KafkaConsumer<String, String> consumer = KafkaConsumer.create(vertx, config);
		// subscribe to several topics with list
		Set<String> topics = new HashSet<>();
		topics.add("test");
		consumer.subscribe(topics);
		System.out.println("consumer created");

		vertx.setPeriodic(5000, timerId -> {
			System.out.println("polling");
			consumer.poll(Duration.ofMillis(100)).onSuccess(records -> {
				for (int i = 0; i < records.size(); i++) {
					KafkaConsumerRecord<String, String> record = records.recordAt(i);
//					System.out.println("value=" + record.value());
					System.out.println(record.value());
					MongoTest m2=new MongoTest(vertx);
					m2.insert(record.value());
				}
				consumer.commit();
			}).onFailure(c -> {
				System.out.println();
			});

		});

	}
	
	private void setKafkaConsumer() {
		Map<String, String> config = new HashMap<>();
		config.put("bootstrap.servers", "localhost:9092");
		config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("group.id", "my_group");
		config.put("auto.offset.reset", "earliest");
		config.put("enable.auto.commit", "false");
		
		// use consumer for interacting with Apache Kafka
		KafkaConsumer<String, String> consumer = KafkaConsumer.create(vertx, config);
		
		// subscribe to several topics with list	
		consumer.subscribe("test");
		System.out.println("consumer created");
		consumer.handler(record -> {
			
			  System.out.println(record);
			  System.out.println(record.value());
				MongoTest m2=new MongoTest(vertx);
				m2.insert(record.value());
			  consumer.commit();
		});
		
		
		
	}

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new java_kafka());
	}
}
