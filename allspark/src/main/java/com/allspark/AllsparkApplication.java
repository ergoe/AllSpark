package com.allspark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class AllsparkApplication  extends SpringBootServletInitializer {

	//Added to make .war
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AllsparkApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AllsparkApplication.class, args);
	}
}
