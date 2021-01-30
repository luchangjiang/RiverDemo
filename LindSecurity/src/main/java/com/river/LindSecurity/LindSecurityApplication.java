package com.river.LindSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LindSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LindSecurityApplication.class, args);
	}

<<<<<<< HEAD
	/*@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(
			@Value("${spring.application.name}") String applicationName) {
		return (registry) -> registry.config().commonTags("application", applicationName);
	}*/
=======
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
}
