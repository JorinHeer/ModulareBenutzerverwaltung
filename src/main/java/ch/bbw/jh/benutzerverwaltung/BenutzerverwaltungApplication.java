package ch.bbw.jh.benutzerverwaltung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BenutzerverwaltungApplication {
	private static final Logger logger = LoggerFactory.getLogger(BenutzerverwaltungApplication.class);
	public static void main(String[] args) {
		logger.info("message");
		SpringApplication.run(BenutzerverwaltungApplication.class, args);
	}

}
