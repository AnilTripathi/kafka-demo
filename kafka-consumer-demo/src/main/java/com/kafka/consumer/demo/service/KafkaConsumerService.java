package com.kafka.consumer.demo.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.consumer.demo.utils.Constant;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = Constant.TOPIC_NAME, groupId = Constant.GROUP_ID)
    public void listen(ConsumerRecord<String, String> record) {
        System.out.printf("Consumed message: %s%n", record.value());
    }
}

