package com.iccm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.iccm.*")
public class WorkMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkMonitorApplication.class, args);
	}

}
