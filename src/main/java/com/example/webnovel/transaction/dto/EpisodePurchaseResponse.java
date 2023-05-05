package com.example.webnovel.transaction.dto;

public class EpisodePurchaseResponse {
    private final Long episodePurchaseId;

    public EpisodePurchaseResponse(Long episodePurchaseId) {
        this.episodePurchaseId = episodePurchaseId;
    }

    public String getMessage() {
        return "단편을 성공적으로 구매하였습니다.";
    }

    public Long getEpisodePurchaseId() {
        return episodePurchaseId;
    }
}
