package com.kafka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerComponent {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg()
    {
        kafkaTemplate.send("test", "practice-producer");
    }




}
