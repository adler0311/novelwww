package com.example.webnovel.transaction.persistence;

import com.example.webnovel.aggregation.dto.EpisodePurchaseAggregateDto;
import com.example.webnovel.novel.dto.FavoriteNovelDto;
import com.example.webnovel.transaction.persistence.EpisodePurchase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EpisodePurchaseRepository extends JpaRepository<EpisodePurchase, Long> {

    Optional<EpisodePurchase> findByEpisodeIdAndUserId(Long episodeId, Long userId);
    @Query("SELECT new com.example.webnovel.aggregation.dto.EpisodePurchaseAggregateDto(ep.episode.id, count(ep.episode.id)) FROM EpisodePurchase ep GROUP BY ep.episode.id")
    List<EpisodePurchaseAggregateDto> getEpisodeAggregatedResult();

}
