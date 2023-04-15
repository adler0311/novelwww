package com.example.webnovel.controller.schema;

import com.example.webnovel.persistence.Novel;

import javax.persistence.Column;

public class NovelListItemResponse {
    private String title;

    private String author;

    private String description;

    private String genre;

    private Long purchaseCount;

    public NovelListItemResponse(Novel novel) {
        this.author = novel.getAuthor();
        this.title = novel.getTitle();
        this.description = novel.getDescription();
        this.genre = novel.getGenre();
        this.purchaseCount = novel.getPurchaseCount();
    }

    public Long getPurchaseCount() {
        return purchaseCount;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }
}
