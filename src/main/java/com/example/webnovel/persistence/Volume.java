package com.example.webnovel.persistence;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "volume")
public class Volume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(nullable = false)
    private String title;

    @CreationTimestamp
    @Column(name = "written_datetime", nullable = false, updatable = false)
    private LocalDateTime writtenDateTime;

    @Column(name = "series_number", nullable = false)
    private Integer seriesNumber;

    @Column(name = "number_of_pages", nullable = false)
    private Integer numberOfPages;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id", nullable = false)
    private Novel novel;

    @Version
    @Column(name="version")
    private Long version;

    public Volume(String title, LocalDateTime writtenDateTime, Integer seriesNumber, Integer numberOfPages, Long fileSize, Boolean isPaid) {
        this.title = title;
        this.writtenDateTime = writtenDateTime;
        this.seriesNumber = seriesNumber;
        this.numberOfPages = numberOfPages;
        this.fileSize = fileSize;
        this.isPaid = isPaid;
    }

    public Volume() {

    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }
}
