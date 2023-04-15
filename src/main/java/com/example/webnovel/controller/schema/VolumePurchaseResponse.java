package com.example.webnovel.controller.schema;

public class VolumePurchaseResponse {
    private final Long volumePurchaseId;

    public VolumePurchaseResponse(Long volumePurchaseId) {
        this.volumePurchaseId = volumePurchaseId;
    }

    public String getMessage() {
        return "단편을 성공적으로 구매하였습니다.";
    }

    public Long getVolumePurchaseId() {
        return volumePurchaseId;
    }
}
