package com.kafka.demo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {

    //定义成员 KafkaConsumer
    KafkaConsumer<Integer, String> consumer;
    //定义topic
    String topic;

    /**
     * 初始化consumer
     */
    public Consumer(String topic) {
        //初始化properties
        Properties properties = new Properties();
        //连接参数
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "120.79.226.150:9092");
        //group id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "practice-consumer");
        //ENABLE_AUTO_COMMIT_CONFIG
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // SESSIONG_TIMEOUT_MS_CONFIG 30000
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        // keySerialize
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        //valueSerialize
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //AUTO_OFFSET_RESET_CONFIG
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //初始化consumer
        consumer = new KafkaConsumer<Integer, String>(properties);
        //topic
        this.topic = topic;
    }


    /**
     * 重写run
     */
    @Override
    public void run() {
        //死循环
        while (true) {
            //订阅topic
            consumer.subscribe(Collections.singleton(topic));
            //poll 获取消息
            ConsumerRecords<Integer, String> poll = consumer.poll(Duration.ofSeconds(1));
            //输出消息
            poll.forEach((record) -> System.out.println(record.key() + " : " + record.value() + " : " + record.offset()));
        }
    }


    /**
     * main
     */
    public static void main(String[] args) {
        new Consumer("test").start();
    }
}
