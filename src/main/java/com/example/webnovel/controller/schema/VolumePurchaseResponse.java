package com.example.webnovel.controller.schema;

import java.util.UUID;

public class VolumePurchaseResponse {
    private final UUID volumePurchaseId;

    public VolumePurchaseResponse(UUID volumePurchaseId) {
        this.volumePurchaseId = volumePurchaseId;
    }

    public String getMessage() {
        return "단편을 성공적으로 구매하였습니다.";
    }

    public UUID getVolumePurchaseId() {
        return volumePurchaseId;
    }
}
