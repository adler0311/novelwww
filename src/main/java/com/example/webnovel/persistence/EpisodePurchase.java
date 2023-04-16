package com.example.webnovel.persistence;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "episode_purchase", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"episode_id", "user_id"}),
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

    @Column(name="create_dt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDt;


    @Column(name="update_dt", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDt;


    public EpisodePurchase(Episode episode, User user) {
        this.episode = episode;
        this.user = user;
    }

    public EpisodePurchase() {

    }

    public Long getId() {
        return id;
    }

}
