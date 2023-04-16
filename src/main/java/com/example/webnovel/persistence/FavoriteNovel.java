package com.example.webnovel.persistence;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    @Column(name="create_dt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDt;


    @Column(name="update_dt", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDt;


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
