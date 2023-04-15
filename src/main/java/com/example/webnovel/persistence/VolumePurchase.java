package com.example.webnovel.persistence;

import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "volume_purchase", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"volumeId", "userId"}),
})
@SQLInsert(sql = "INSERT IGNORE INTO volume_purchase(id, novel_id, volume_id, user_id) VALUES (?, ?, ?, ?)" )
public class VolumePurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long volumeId;

    @Column(nullable = false)
    private UUID novelId;

    public VolumePurchase(UUID novelId, Long volumeId, Long userId) {
        this.novelId = novelId;
        this.volumeId = volumeId;
        this.userId = userId;
    }

    public VolumePurchase() {

    }

    public Long getId() {
        return id;
    }

    public Long getVolumeId() {
        return volumeId;
    }

    public UUID getNovelId() {
        return novelId;
    }

    public Long getUserId() {
        return userId;
    }
}
