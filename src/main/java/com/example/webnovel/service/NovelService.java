package com.example.webnovel.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.webnovel.persistence.Novel;
import com.example.webnovel.persistence.NovelRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class NovelService {
    private final NovelRepository novelRepository;

    public NovelService(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    public Long createNovel(String title, String author, String description, String genre, Integer totalPage) {
        Novel novel = new Novel(title, author, description, genre, totalPage);
        Novel r = novelRepository.save(novel);
        return r.getId();
    }

    @Cacheable("bestSellers")
    public List<Novel> getBestSellers() {
        System.out.println("get best sellers no cache");
        return novelRepository.getBestSellers();
    }

    @Scheduled(fixedDelay = 1_000*60)
    @CachePut("bestSellers")
    public List<Novel> updateBestsellerNovelCache() {
        System.out.println("update best seller novel cache");
        return novelRepository.getBestSellers();
    }

//    @Scheduled(fixedDelay = 1_000*20)
    public void scheduleBestSellerCache() {
        try {
            updateBestsellerNovelCache();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
