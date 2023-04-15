package com.example.webnovel.persistence;

import com.example.webnovel.service.FavoriteNovelDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteNovelRepository extends JpaRepository<FavoriteNovel, Long> {

    @Query("SELECT new com.example.webnovel.service.FavoriteNovelDto(fn.novelId, fn.userId, n.title, fn.readPage, n.totalPages) FROM FavoriteNovel fn \n" +
            "join Novel n\n" +
            "on fn.novelId = n.id\n" +
            "where fn.userId= :userId")
    List<FavoriteNovelDto> getFavoriteNovels(Long userId, Pageable pageable);

}
