package com.example.webnovel.episode.persistence;

import com.example.webnovel.episode.persistence.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    @Query("SELECT COALESCE(MAX(v.seriesNumber), 0) FROM Episode v")
    Integer findMaxSeriesNumber();

}
