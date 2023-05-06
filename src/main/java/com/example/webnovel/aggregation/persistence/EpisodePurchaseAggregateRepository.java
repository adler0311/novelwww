package com.example.webnovel.aggregation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EpisodePurchaseAggregateRepository extends JpaRepository<EpisodePurchaseAggregate, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO episode_purchase_aggregate (id, episode_id, purchase_count, aggregate_at) " +
            "VALUES (NULL, :episode_id, :purchase_count, LOCALTIMESTAMP) " +
            "ON DUPLICATE KEY UPDATE " +
            "purchase_count = :purchase_count, aggregate_at = LOCALTIMESTAMP", nativeQuery = true)
    void upsert(Long episode_id, Long purchase_count);



}
