package com.river.jsonDemo;

import com.river.jsonDemo.config.SpringContextHolder;
import com.river.jsonDemo.service.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class JsonDemoApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(JsonDemoApplication.class, args);


	}

}
