package com.alvarohdr.gastosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GastosApiApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
		SpringApplication.run(GastosApiApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO: Modify cors paths
		registry.addMapping("/**")
				.allowedMethods("*");
	}
}
