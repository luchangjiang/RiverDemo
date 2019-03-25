package com.river.ApplicationStartedDemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * @program: RiverDemo
 * @description:
 * @author: luchangjiang
 * @create: 2019-03-25 15:56
 **/
@Slf4j
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        log.info("......ApplicationStartingEvent......");
    }
}
