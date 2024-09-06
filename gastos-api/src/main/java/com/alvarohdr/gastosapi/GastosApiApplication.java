package com.alvarohdr.gastosapi;

import com.alvarohdr.framework.config.JwtAuthenticationFilter;
import com.alvarohdr.framework.config.JwtUtils;
import com.alvarohdr.framework.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Import({SecurityConfig.class, JwtUtils.class, JwtAuthenticationFilter.class})
public class GastosApiApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
		SpringApplication.run(GastosApiApplication.class, args);
	}

}
