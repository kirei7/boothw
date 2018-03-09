package com.epam.rd.boothw;

import com.epam.rd.boothw.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AppConfig.class})
public class BoothwApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoothwApplication.class, args);
	}
}
