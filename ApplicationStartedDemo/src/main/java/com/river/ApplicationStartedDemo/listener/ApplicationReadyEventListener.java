package com.river.ApplicationStartedDemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @program: RiverDemo
 * @description:
 * @author: luchangjiang
 * @create: 2019-03-25 15:02
 **/
@Slf4j
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("......ApplicationReadyEvent......");
    }

}
