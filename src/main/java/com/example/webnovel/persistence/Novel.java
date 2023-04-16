package com.example.webnovel.persistence;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long purchaseCount;

    @Column(nullable = false)
    private Integer totalPages;

    @Column(name="create_dt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDt;


    @Column(name="update_dt", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDt;


    @OneToMany(mappedBy = "novel" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Episode> episodes = new ArrayList<>();

    public Novel() {
    }

    public Novel(String title, String author, String description, String genre, Integer totalPages) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.purchaseCount = 0L;
        this.totalPages = totalPages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setPurchaseCount(Long purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Long getPurchaseCount() {
        return purchaseCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}