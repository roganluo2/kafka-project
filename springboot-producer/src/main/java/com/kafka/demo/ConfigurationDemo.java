package com.kafka.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class ConfigurationDemo {


    @Bean
    public KafkaTemplate getKafkaTemplate(KafkaTemplate kafkaTemplate)
    {
        System.out.println("kafkaTemplate 初始化");
        return kafkaTemplate;
    }


}
