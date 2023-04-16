package com.example.webnovel;

import com.example.webnovel.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@EnableScheduling
@SpringBootApplication
public class WebNovelApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebNovelApplication.class, args);
	}


}
