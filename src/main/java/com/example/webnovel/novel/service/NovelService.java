package com.example.webnovel.novel.service;

import com.example.webnovel.novel.persistence.Novel;
import com.example.webnovel.novel.persistence.NovelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NovelService {
    private final Logger log = LoggerFactory.getLogger(getClass());

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
        log.info("get best sellers no cache");
        return novelRepository.getBestSellers();
    }

    @Scheduled(fixedDelay = 1_000*60)
    @CachePut("bestSellers")
    public List<Novel> updateBestsellerNovelCache() {
        log.info("update best seller novel cache");
        return novelRepository.getBestSellers();
    }
}
