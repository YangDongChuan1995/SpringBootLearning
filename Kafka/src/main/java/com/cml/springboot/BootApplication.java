package com.cml.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.cml.springboot.kafka.Producer;
import com.cml.springboot.message.TestMessage;

@SpringBootApplication
@EnableScheduling
@EnableKafka
public class BootApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BootApplication.class, args);
	}

	@Autowired
	private Producer producer;

	@Scheduled(fixedRate = 2000)
	public void scheduleSendMessage() {
		producer.send(new TestMessage((int) (Math.random() * 1000), "A simple test message"));
		producer.send("Test===>" + System.currentTimeMillis());
	}

	// @Bean
	// public ConcurrentKafkaListenerContainerFactory
	// myKafkaListenerContainerFactory() {
	// ConcurrentKafkaListenerContainerFactory factory = new
	// ConcurrentKafkaListenerContainerFactory();
	// factory.setConcurrency(4);
	// return factory;
	// }

	// @Bean
	// public ApplicationRunner runner(Producer producer) {
	// return (args) -> {
	// new Thread(() -> {
	// int i = 0;
	// while (i < 10) {
	// producer.send(new TestMessage(1, "A simple test message" + i++));
	// try {
	// Thread.sleep(1000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	// }).start();
	//
	// };
	// }
}