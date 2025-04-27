package com.example.commanddecorator;

import com.example.commanddecorator.controller.RemoteController;
import com.example.commanddecorator.decorator.RegexIterable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CommandDecoratorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CommandDecoratorApplication.class, args);
	}

	@Override
	public void run(String... args) {

		RemoteController.demo();
		RegexIterable.demo();
		System.exit(0);
	}
}
