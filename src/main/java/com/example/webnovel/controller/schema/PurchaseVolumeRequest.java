package com.example.webnovel.controller.schema;

public class PurchaseVolumeRequest {
    private Long userId;

    public PurchaseVolumeRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
