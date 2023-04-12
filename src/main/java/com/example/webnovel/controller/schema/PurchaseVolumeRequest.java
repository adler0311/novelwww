package com.example.webnovel.controller.schema;

import java.util.UUID;

public class PurchaseVolumeRequest {
    private UUID userId;

    public PurchaseVolumeRequest(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}
