package com.example.webnovel.novel.dto;

public class FavoriteNovelDto {
    private Long novelId;
    private Long userId;

    private String novelTitle;
    private Integer readPage;
    private Integer totalPages;


    public FavoriteNovelDto(Long novelId, Long userId, String novelTitle, Integer readPage, Integer totalPages) {
        this.novelId = novelId;
        this.userId = userId;
        this.novelTitle = novelTitle;
        this.readPage = readPage;
        this.totalPages = totalPages;
    }

    public Long getNovelId() {
        return novelId;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getReadPage() {
        return readPage;
    }

    public String getNovelTitle() {
        return novelTitle;
    }

    public Long getUserId() {
        return userId;
    }
}
