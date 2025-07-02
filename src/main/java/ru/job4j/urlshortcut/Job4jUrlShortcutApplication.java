package ru.job4j.urlshortcut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Job4jUrlShortcutApplication {

	public static void main(String[] args) {
		SpringApplication.run(Job4jUrlShortcutApplication.class, args);
	}

}
