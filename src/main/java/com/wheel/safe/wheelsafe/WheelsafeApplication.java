package com.wheel.safe.wheelsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties(prefix = "app.jwt")
public class WheelsafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WheelsafeApplication.class, args);
	}
	
}
