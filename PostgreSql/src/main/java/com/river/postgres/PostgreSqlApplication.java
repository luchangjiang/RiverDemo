package com.river.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
<<<<<<< HEAD
//@EnableEncryptableProperties
=======
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
public class PostgreSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgreSqlApplication.class, args);
	}

}
