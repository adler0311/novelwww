package com.example.webnovel.controller.schema;

public class EpisodePurchaseRequest {
    private Long userId;

    public EpisodePurchaseRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
