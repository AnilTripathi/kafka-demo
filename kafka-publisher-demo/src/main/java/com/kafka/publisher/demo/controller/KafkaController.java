package com.kafka.publisher.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.publisher.demo.service.KafkaProducerService;
import com.kafka.publisher.demo.utils.Constant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/kafka")
@Tag(name = "Kafka Controller", description = "Operations to send message to Kafka topic")
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/create-topic")
    public String createTopic(@RequestParam String topicName,@RequestParam int partition,@RequestParam short replicationFactor) {
        kafkaProducerService.createTopic(topicName, partition, replicationFactor);
        return "Topic created: " + topicName;
    }

    @PostMapping("/send")
    @Operation(summary = "Send message", description = "Send message to kafka topic and return success message")
    public String sendMessage(@RequestBody String message) {
        kafkaProducerService.sendMessage(Constant.TOPIC_NAME, message);
        return "Message sent to Kafka topic";
    }
}
