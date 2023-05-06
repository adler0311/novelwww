package com.example.webnovel.aggregation.dto;

import java.time.LocalDateTime;

public class EpisodePurchaseAggregateDto {
    private Long episodeId;
    private Long count;

    private LocalDateTime aggregateAt;

    public EpisodePurchaseAggregateDto(Long episodeId, Long count) {
        this.episodeId = episodeId;
        this.count = count;
        this.aggregateAt = LocalDateTime.now();
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public Long getCount() {
        return count;
    }

    public LocalDateTime getAggregateAt() {
        return aggregateAt;
    }
}
