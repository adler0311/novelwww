package com.example.webnovel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;

public interface VolumePurchaseRepository extends JpaRepository<VolumePurchase, Long> {

    Optional<VolumePurchase> findByVolumeIdAndUserId(Long volumeId, Long userId);
}
