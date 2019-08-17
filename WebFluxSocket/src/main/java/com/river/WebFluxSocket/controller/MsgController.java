package com.river.WebFluxSocket.controller;

import com.river.WebFluxSocket.session.WebSocketSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-08-17 18:52
 **/
@RestController
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    private ConcurrentHashMap<String, WebSocketSender> senderMap;
    @PostMapping("/send")
    public String sendMessage(@RequestParam String id, @RequestParam String data) {
        WebSocketSender sender = senderMap.get(id);
        if (sender != null) {
            sender.sendData(data);
            return String.format("Message '%s' sent to connection: %s.", data, id);
        } else {
            return String.format("Connection of id '%s' doesn't exist", id);
        }
    }

    @GetMapping("/echo")
    public String echo(Model model){
        return "echo.html";
    }
}
