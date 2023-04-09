package com.example.webnovel.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.webnovel.persistence.Novel;
import com.example.webnovel.persistence.NovelRepository;

@Service
public class NovelService {
    private final NovelRepository novelRepository;

    public NovelService(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    public UUID createNovel(String title, String author, String description, String genre) {
        Novel novel = new Novel(title, author, description, genre);
        Novel r = novelRepository.save(novel);
        return r.getId();
    }

}
