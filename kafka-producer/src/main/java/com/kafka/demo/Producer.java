package com.kafka.demo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Producer extends Thread {

    //1. KafkaProducer成员变量，方便公用
    KafkaProducer<Integer,String> producer;
    //2. 定义topic
    String topic;

    /**
     * 初始化Producer
     */
    public Producer(String topic) {

        //初始化Properties
        Properties properties = new Properties();
        //properties 设置连接配置
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"120.79.226.150:9092");
        //设置ProducerConfig.CLIENT_ID_CONFIG,"practice-producer"
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"practice-producer");
        //设置Key_Serializer_class_config IntegerSerializer.
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        //设置Value_Serializer_class StringSerializer
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //初始化producer
        producer = new KafkaProducer<Integer,String>(properties);
        //设置成员变量topic
        this.topic = topic;
    }


    /**
     * 重写继承run方法
     */
    @Override
    public void run() {
        //通过while发50消息
        int num =0;
        while (num ++ < 50) {
            //定义msg
            String msg = "producer send msg num: " + num;
            //通过producer发送消息，ProducerRecord,通过callback 指定异步发送
            producer.send(new ProducerRecord<>(topic,msg), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println(metadata.topic() + " : " + metadata.offset() +  " :" + metadata.partition());
                 }
            });
            //睡眠2s
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * main 启动
     */

    public static void main(String[] args) {
        new Producer("test").start();
    }
}
