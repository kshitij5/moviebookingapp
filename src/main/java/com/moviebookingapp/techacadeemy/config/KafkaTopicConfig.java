//package com.moviebookingapp.techacadeemy.config;
//
//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//import org.springframework.kafka.core.KafkaAdmin;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaTopicConfig {
//	@Bean
//	public NewTopic totalTicketBookedTopic() {
//		return TopicBuilder.name("totalTicketBooked").build();
//	}
//	@Bean
//	public NewTopic totalTicketAvailableTopic() {
//		return TopicBuilder.name("totalTicketAvailable").build();
//	}
//	@Bean
//	public NewTopic updateTicketStatusTopic() {
//		return TopicBuilder.name("updateTicketStatus").build();
//	}
//}