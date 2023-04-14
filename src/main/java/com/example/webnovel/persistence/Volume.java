package com.example.webnovel.persistence;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Volume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Novel novel;

    @Version
    @Column
    private Long version;

    public Volume(String title, LocalDateTime writtenDateTime, Integer seriesNumber, Integer numberOfPages, Long fileSize, Long pointForPurchase) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.writtenDateTime = writtenDateTime;
        this.seriesNumber = seriesNumber;
        this.numberOfPages = numberOfPages;
        this.fileSize = fileSize;
        this.pointForPurchase = pointForPurchase;
    }

    public Volume() {

    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public Long getPointForPurchase() {
        return this.pointForPurchase;
    }

    public UUID getId() {
        return id;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }
}
