package com.kafka.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsumerComponent {


    @KafkaListener(topics = {"test"})
    public void sendMsg(ConsumerRecord consumerRecord)
    {
        System.out.println(Optional.ofNullable(consumerRecord).map(ConsumerRecord::value));

    }




}
