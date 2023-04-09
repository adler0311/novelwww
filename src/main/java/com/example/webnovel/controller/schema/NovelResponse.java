package com.example.webnovel.controller.schema;

import java.util.UUID;

public class NovelResponse {
    private String message = "소설이 성공적으로 등록되었습니다.";
    private UUID novelId;

    public NovelResponse(UUID novelId2) {
        this.novelId = novelId2;
    }

    public void setNovelId(UUID novelId) {
        this.novelId = novelId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public UUID getNovelId() {
        return novelId;
    }

    // Getter
}
