package com.example.webnovel.persistence;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false)
    private String title;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime writtenDateTime;

    @Column(nullable = false)
    private Integer seriesNumber;

    @Column(nullable = false)
    private Integer numberOfPages;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private Long pointForPurchase;

    @Column(name="create_dt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDt;


    @Column(name="update_dt", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Novel novel;

    @Version
    @Column
    private Long version;

    public Episode(String title, LocalDateTime writtenDateTime, Integer seriesNumber, Integer numberOfPages, Long fileSize, Long pointForPurchase) {
        this.title = title;
        this.writtenDateTime = writtenDateTime;
        this.seriesNumber = seriesNumber;
        this.numberOfPages = numberOfPages;
        this.fileSize = fileSize;
        this.pointForPurchase = pointForPurchase;
    }

    public Episode() {

    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public Long getPointForPurchase() {
        return this.pointForPurchase;
    }

    public Long getId() {
        return id;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }
}
