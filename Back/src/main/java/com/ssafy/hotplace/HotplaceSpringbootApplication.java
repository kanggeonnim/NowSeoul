package com.ssafy.hotplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})
public class HotplaceSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotplaceSpringbootApplication.class, args);
	}

}
