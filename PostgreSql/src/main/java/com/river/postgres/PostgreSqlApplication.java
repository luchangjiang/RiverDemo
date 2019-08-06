package com.river.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class PostgreSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgreSqlApplication.class, args);
	}

}
