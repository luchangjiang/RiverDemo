package com.river.directrabbit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title: ReceiveMessage
 * @ProjectName springboot_rabbit_mq
 * @Description: 接收消息
 * @Author WeiShiHuai
 * @Date 2018/9/20 15:25
 */
@Component
@RabbitListener(queues = "user.save.queue.name")
public class ReceiveMessage {
    private static Logger logger = LoggerFactory.getLogger(ReceiveMessage.class);

    @RabbitHandler
    public void receiveMessage(String userName) {
        logger.info("消息接收成功，用户名为：" + userName);
        //可以添加自定义业务逻辑处理
    }

}
