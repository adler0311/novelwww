package com.example.webnovel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EpisodePurchaseRepository extends JpaRepository<EpisodePurchase, Long> {

    Optional<EpisodePurchase> findByEpisodeIdAndUserId(Long episodeId, Long userId);
}
