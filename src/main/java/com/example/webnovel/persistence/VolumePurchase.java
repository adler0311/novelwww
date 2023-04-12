package com.example.webnovel.persistence;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="volume_purchase", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"volumeId", "userId"})
})
public class VolumePurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID userId;

    private UUID volumeId;

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
}
