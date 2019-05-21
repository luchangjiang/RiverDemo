package com.river.RedisQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @RequestMapping("sendMessage1/{name}")
    public String sendMessage1(@PathVariable("name") String name) {
        return publisherService.sendMessage1(name);
    }

    @RequestMapping("sendMessage2/{name}")
    public String sendMessage2(@PathVariable("name") String name) {
        return publisherService.sendMessage2(name);
    }
}
