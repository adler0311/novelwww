package com.example.webnovel.aggregation.persistence;

import com.example.webnovel.aggregation.dto.EpisodePurchaseAggregateDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name="episode_purchase_aggregate",
        uniqueConstraints = @UniqueConstraint(columnNames = {"novel_id", "episode_id"})
)
public class EpisodePurchaseAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "novel_id")
    private Long novelId;

    @Column(name = "episode_id")
    private Long episodeId;
    @Column(name = "purchase_count")
    private Long purchaseCount;

    private LocalDateTime aggregateAt;

    public EpisodePurchaseAggregate(EpisodePurchaseAggregateDto episodePurchaseAggregateDto) {
        this.episodeId = episodePurchaseAggregateDto.getEpisodeId();
        this.purchaseCount = episodePurchaseAggregateDto.getPurchaseCount();
        this.aggregateAt = episodePurchaseAggregateDto.getAggregateAt();
    }

    public EpisodePurchaseAggregate() {

    }
}
