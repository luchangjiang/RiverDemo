package com.river.ApplicationStartedDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}
