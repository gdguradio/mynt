package com.example.mynt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MyntApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyntApplication.class, args);
	}

	@Bean
	public MyBean myBean() {
		return new MyBean();
	}

	public class MyBean {

		@PostConstruct
		public void onApplicationStart() {
			// Code to run when application starts
			System.out.println("My application has started!");
		}

	}
	@Component
	public class MyComponent {

		@EventListener(ApplicationReadyEvent.class)
		public void doSomethingAfterStartup() {
			// Add some code here to keep the application running
			// For example, you can use an infinite loop
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}