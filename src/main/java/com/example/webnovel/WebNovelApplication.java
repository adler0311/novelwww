package com.example.webnovel;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableBatchProcessing
@EnableRetry
@EnableScheduling
@SpringBootApplication
public class WebNovelApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebNovelApplication.class, args);
	}


}
