package com.kafka.publisher.demo.service;

import java.util.Collections;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final AdminClient adminClient;
    
    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, AdminClient adminClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.adminClient = adminClient;
    }

    public void sendMessage(String topic, String message) {
        for (int i = 0; i < 10000; i++){
            kafkaTemplate.send(topic, message + i);
        }
    }

    public void createTopic(String topicName, int partitions, short replicationFactor) {
        NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);
        adminClient.createTopics(Collections.singleton(newTopic)).all().whenComplete((v, t) -> {
            if (t != null) {
                System.out.println("Error creating topic: " + t.getMessage());
            } else {
                System.out.println("Topic created: " + topicName);
            }
        });
    }
}

