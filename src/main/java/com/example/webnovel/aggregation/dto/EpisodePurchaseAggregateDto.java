package com.example.webnovel.aggregation.dto;

import java.time.LocalDateTime;

public class EpisodePurchaseAggregateDto {

    private Long novelId;
    private Long episodeId;
    private Long purchaseCount;

    private LocalDateTime aggregateAt;

    public EpisodePurchaseAggregateDto(Long novelId, Long episodeId, Long purchaseCount) {
        this.novelId = novelId;
        this.episodeId = episodeId;
        this.purchaseCount = purchaseCount;
        this.aggregateAt = LocalDateTime.now();
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public Long getPurchaseCount() {
        return purchaseCount;
    }

    public LocalDateTime getAggregateAt() {
        return aggregateAt;
    }

    public Long getNovelId() {
        return novelId;
    }
}
