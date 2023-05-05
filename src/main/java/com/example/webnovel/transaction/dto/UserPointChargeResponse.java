package com.example.webnovel.transaction.dto;

public class UserPointChargeResponse {
    private Long currentPoint;
    public UserPointChargeResponse(Long point) {
        this.currentPoint = point;
    }

    public Long getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Long currentPoint) {
        this.currentPoint = currentPoint;
    }
}
