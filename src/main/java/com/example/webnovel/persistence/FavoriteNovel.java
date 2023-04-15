package com.example.webnovel.persistence;

import javax.persistence.*;

@Entity
public class FavoriteNovel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long novelId;

    @Column(nullable = false)
    private Integer readPage;

    public FavoriteNovel(Long novelId, Long userId) {
        this.novelId = novelId;
        this.userId = userId;
        this.readPage = 0;
    }

    public FavoriteNovel() {

    }

    public Integer getReadPage() {
        return readPage;
    }
}
