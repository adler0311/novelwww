package com.example.webnovel.episode.persistence;

import com.example.webnovel.user.persistence.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EpisodeRead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="episode_id", nullable = false)
    private Episode episode;

    @Column(name="create_dt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDt;


    @Column(name="update_dt", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDt;

    @Column(name="read_page", nullable = false)
    private Integer readPage;

    public EpisodeRead(User purchaseUser, Episode episodeToPurchase) {
        this.user = purchaseUser;
        this.episode = episodeToPurchase;
        this.readPage = 0;
    }
}
