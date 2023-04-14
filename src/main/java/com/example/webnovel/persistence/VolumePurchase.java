package com.example.webnovel.persistence;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"volumeId", "userId"})
})
public class VolumePurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID volumeId;

    @Column(nullable = false)
    private UUID novelId;

    public VolumePurchase(UUID novelId, UUID volumeId, UUID userId) {
        this.id = UUID.randomUUID();
        this.novelId = novelId;
        this.volumeId = volumeId;
        this.userId = userId;
    }

    public VolumePurchase() {

    }

    public UUID getId() {
        return id;
    }

    public UUID getVolumeId() {
        return volumeId;
    }

    public UUID getNovelId() {
        return novelId;
    }

    public UUID getUserId() {
        return userId;
    }
}
