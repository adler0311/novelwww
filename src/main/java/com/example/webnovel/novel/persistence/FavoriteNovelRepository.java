package com.example.webnovel.novel.persistence;

import com.example.webnovel.novel.dto.FavoriteNovelDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteNovelRepository extends JpaRepository<FavoriteNovel, Long> {

    @Query("SELECT new com.example.webnovel.novel.dto.FavoriteNovelDto(fn.novelId, fn.userId, n.title, fn.readPage, n.totalPages) FROM FavoriteNovel fn \n" +
            "join Novel n\n" +
            "on fn.novelId = n.id\n" +
            "where fn.userId= :userId")
    List<FavoriteNovelDto> getFavoriteNovels(Long userId, Pageable pageable);

}
