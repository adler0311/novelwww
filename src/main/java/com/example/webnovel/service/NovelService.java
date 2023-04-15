package com.example.webnovel.service;

import org.springframework.stereotype.Service;

import com.example.webnovel.persistence.Novel;
import com.example.webnovel.persistence.NovelRepository;

@Service
public class NovelService {
    private final NovelRepository novelRepository;

    public NovelService(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    public Long createNovel(String title, String author, String description, String genre) {
        Novel novel = new Novel(title, author, description, genre);
        Novel r = novelRepository.save(novel);
        return r.getId();
    }

}
