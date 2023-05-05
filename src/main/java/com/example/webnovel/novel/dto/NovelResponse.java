package com.example.webnovel.novel.dto;

public class NovelResponse {
    private String message = "소설이 성공적으로 등록되었습니다.";
    private Long novelId;

    public NovelResponse(Long novelId2) {
        this.novelId = novelId2;
    }

    public void setNovelId(Long novelId) {
        this.novelId = novelId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Long getNovelId() {
        return novelId;
    }

    // Getter
}
