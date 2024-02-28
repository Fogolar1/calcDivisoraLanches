package com.calcLanches.divisoraLanches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DivisoraLanchesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DivisoraLanchesApplication.class, args);
	}

}
