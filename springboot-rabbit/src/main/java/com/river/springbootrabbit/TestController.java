package com.river.springbootrabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：River
 * @date ：Created in 5/29/2019 12:25 PM
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private HelloSender helloSender;

    /**
     * 单生产者-单个消费者
     */
    @PostMapping("/send")
    public String hello() throws Exception {
        helloSender.send();
        return "Success";
    }

}
