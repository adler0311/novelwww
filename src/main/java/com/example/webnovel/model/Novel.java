package com.example.webnovel.model;

import java.util.UUID;

public class Novel {

    private UUID id;
    private String title;
    private String author;
    private String description;
    private String genre;

    public Novel(String title2, String author2, String description2, String genre2) {
        this.id = UUID.randomUUID();
        this.title = title2;
        this.author = author2;
        this.description = description2;
        this.genre = genre2;
    }

}
