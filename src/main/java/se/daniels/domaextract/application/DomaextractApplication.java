package se.daniels.domaextract.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DomaextractApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DomaextractApplication.class, args);
	}
}
