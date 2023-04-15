package com.example.webnovel.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private String genre;

    @OneToMany(mappedBy = "novel" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Volume> volumes = new ArrayList<>();

    public Novel() {
    }

    public Novel(String title, String author, String description, String genre) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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