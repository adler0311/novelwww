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

	@Bean
	public CommandLineRunner insertData(UserRepository userRepository,
										NovelRepository novelRepository,
										FavoriteNovelRepository favoriteNovelRepository) {
		System.out.println("insert data started");
		return args -> {
//    		Random random = new Random();
//			List<User> users = new ArrayList<>();
//			for (int i=0; i < 50000; i++) {
//				User user = new User("test-user " + i, (long) random.nextInt(10000));
//				users.add(user);
//			}
//			userRepository.saveAllAndFlush(users);
//
//			List<Novel> novels = new ArrayList<>();
//			for (int i=0; i < 10; i++) {
//				Novel novel = new Novel("novel-" + i, "author-" + i, "description", "genre", 555);
//				novels.add(novel);
//			}
//			novelRepository.saveAllAndFlush(novels);
//
//
//			for (User user : users) {
//				List<FavoriteNovel> favoriteNovels = new ArrayList<>();
//				for (Novel novel : novels) {
//					favoriteNovels.add(new FavoriteNovel(novel.getId(), user.getId()));
//				}
//				favoriteNovelRepository.saveAll(favoriteNovels);
//			}
			System.out.println("data insert finished");
		};
	}

}
