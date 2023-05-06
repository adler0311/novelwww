package com.example.webnovel.transaction.persistence;

import com.example.webnovel.episode.persistence.Episode;
import com.example.webnovel.novel.persistence.Novel;
import com.example.webnovel.user.persistence.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "episode_purchase", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"novel_id", "episode_id", "user_id"}),
})
public class EpisodePurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="episode_id", nullable = false)
    private Episode episode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="novel_id", nullable = false)
    private Novel novel;

    @Column(name="create_dt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDt;


    @Column(name="update_dt", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDt;


    public EpisodePurchase(Episode episode, User user) {
        this.episode = episode;
        this.user = user;
        this.novel = episode.getNovel();
    }

    public EpisodePurchase() {

    }

    public Long getId() {
        return id;
    }

}
