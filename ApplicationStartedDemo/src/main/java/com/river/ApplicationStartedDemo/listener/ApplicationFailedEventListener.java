package com.river.ApplicationStartedDemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @program: RiverDemo
 * @description:
 * @author: luchangjiang
 * @create: 2019-03-25 16:00
 **/
@Slf4j
public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        log.info("......ApplicationFailedEvent......");
    }
}

