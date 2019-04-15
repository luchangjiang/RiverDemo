package com.river.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-04-13 23:50
 **/
@Controller
public class InitController {
    @RequestMapping("/websocket")
    public String init() {
        return "websocket.html";
    }
}
