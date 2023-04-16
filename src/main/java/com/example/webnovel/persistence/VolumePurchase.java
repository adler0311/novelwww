package com.example.webnovel.persistence;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "volume_purchase", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"volume_id", "user_id"}),
})
public class VolumePurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="volume_id", nullable = false)
    private Volume volume;

    @Column(name="create_dt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDt;


    @Column(name="update_dt", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDt;


    public VolumePurchase(Volume volume, User user) {
        this.volume = volume;
        this.user = user;
    }

    public VolumePurchase() {

    }

    public Long getId() {
        return id;
    }

}
