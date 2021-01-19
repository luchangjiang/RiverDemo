package com.river.kafka.listenser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-09-24 23:17
 **/
@Component
@Slf4j
public class DemoListener {

    //声明consumerID为demo，监听topicName为topic.quick.demo的Topic
    @KafkaListener(id = "demo", topics = "topic.quick.demo")
    public void listen(String msgData) {
        log.info("demo receive : "+msgData);
    }
}