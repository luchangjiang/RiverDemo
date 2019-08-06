package com.river.WebSocket.runner;

import com.river.WebSocket.event.MyMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Scheduled(cron = "0/3 * * * * ?")
    public void refreshMsg(){
        publisher.publishEvent(new MyMessageEvent("aaa"));
    }
}
