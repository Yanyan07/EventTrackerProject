package com.skilldistillery.hiker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HikerTrackerApplication extends SpringBootServletInitializer {
	  @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(HikerTrackerApplication.class);
	  }

	public static void main(String[] args) {
		SpringApplication.run(HikerTrackerApplication.class, args);
	}

}
