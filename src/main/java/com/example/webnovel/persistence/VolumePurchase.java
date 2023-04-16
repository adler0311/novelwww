package com.example.webnovel.persistence;

import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

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
