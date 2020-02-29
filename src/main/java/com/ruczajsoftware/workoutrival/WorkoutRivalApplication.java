package com.ruczajsoftware.workoutrival;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class WorkoutRivalApplication {
	public static void main(String[] args) {
		SpringApplication.run(WorkoutRivalApplication.class, args);
	}
}