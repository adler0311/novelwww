package com.example.webnovel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VolumePurchaseRepository extends JpaRepository<VolumePurchase, UUID> {
    Optional<VolumePurchase> findByVolumeIdAndUserId(UUID volumeId, UUID userId);
}
