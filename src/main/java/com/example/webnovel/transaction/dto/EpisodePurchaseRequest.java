package com.example.webnovel.transaction.dto;

public class EpisodePurchaseRequest {
    private Long userId;

    public EpisodePurchaseRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
