package com.example.webnovel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VolumeRepository extends JpaRepository<Volume, Long> {
    @Query("SELECT COALESCE(MAX(v.seriesNumber), 0) FROM Volume v")
    Integer findMaxSeriesNumber();

}
