package com.river.WebSocket.controller;

import com.river.WebSocket.event.MyMessageEvent;
import com.river.WebSocket.message.RequestMessage;
import com.river.WebSocket.message.ResponseMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WsController {
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());

        return new ResponseMessage("welcome," + message.getName() + " !");
    }

    @EventListener(MyMessageEvent.class)
    @SendTo("/topic/listenerMsg")
    public ResponseMessage comSysJob(MyMessageEvent event) {
        String msg = event.getMsg();
        log.info("listererMsg: " + msg);
        return new ResponseMessage("listererMsg: " + msg + " !");
    }
}