package dev.crawford.talkrestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("dev.crawford.model")
@EnableJpaRepositories("dev.crawford.repository")
@SpringBootApplication(scanBasePackages = "dev.crawford")
public class TalkrestappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalkrestappApplication.class, args);
	}

}
