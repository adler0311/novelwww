package com.example.webnovel.controller.schema;

import javax.validation.constraints.NotNull;

public class CreateNovelRequest {
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String description;
    @NotNull
    private String genre;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
