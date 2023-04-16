package com.example.webnovel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    @Query("SELECT COALESCE(MAX(v.seriesNumber), 0) FROM Episode v")
    Integer findMaxSeriesNumber();

}
