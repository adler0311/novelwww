package com.example.webnovel.novel.service;

import com.example.webnovel.novel.persistence.FavoriteNovel;
import com.example.webnovel.novel.persistence.FavoriteNovelRepository;
import com.example.webnovel.novel.dto.FavoriteNovelDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteNovelService {

    private final FavoriteNovelRepository favoriteNovelRepository;

    public FavoriteNovelService(FavoriteNovelRepository favoriteNovelRepository) {
        this.favoriteNovelRepository = favoriteNovelRepository;
    }

    public List<FavoriteNovelDto> getFavoriteNovels(Long userId) {
        return favoriteNovelRepository.getFavoriteNovels(userId, PageRequest.of(0, 10));
    }

    public FavoriteNovel addToFavorite(Long novelId, Long userId) {
        FavoriteNovel favoriteNovel = new FavoriteNovel(novelId, userId);
        return favoriteNovelRepository.save(favoriteNovel);
    }

}
