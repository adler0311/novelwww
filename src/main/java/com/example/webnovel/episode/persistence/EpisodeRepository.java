package com.example.webnovel.episode.persistence;

import com.example.webnovel.episode.persistence.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    @Query("SELECT COALESCE(MAX(v.seriesNumber), 0) FROM Episode v")
    Integer findMaxSeriesNumber();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Episode> findEpisodeById(Long episodeId);
}
