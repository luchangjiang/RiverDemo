package com.river.ApplicationStartedDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.*;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
public class ApplicationStartedDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStartedDemoApplication.class, args);
	}

	@Bean
	public DataLoader dataLoader() {
		return new DataLoader();
	}

	@Slf4j
	static class DataLoader implements CommandLineRunner {

		@Override
		public void run(String... strings) throws Exception {
			log.info("Command line runner ...");
		}
	}

	@EventListener({ApplicationStartingEvent.class})
	public void ApplicationStartingEventListener2(){
		log.info("......ApplicationStartingEvent2......");
	}

	@EventListener({ApplicationEnvironmentPreparedEvent.class})
	public void ApplicationEnvironmentPreparedEventListener2(){
		log.info("......ApplicationEnvironmentPreparedEvent2......");
	}

	@EventListener({ApplicationPreparedEvent.class})
	public void ApplicationPreparedEventListener2(){
		log.info("......ApplicationPreparedEvent2......");
	}

	@EventListener({ApplicationStartedEvent.class})
	public void ApplicationStartedEventListener2(){
		log.info("......ApplicationStartedEvent2......");
	}

	@EventListener({ApplicationReadyEvent.class})
	public void ApplicationReadyEventListener2(){
		log.info("......ApplicationReadyEvent2......");
	}

	@EventListener({ApplicationFailedEvent.class})
	public void ApplicationFailedEventListener2(){
		log.info("......ApplicationFailedEvent2......");
	}

}
