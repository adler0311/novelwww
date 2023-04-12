package com.example.webnovel.service;

import com.example.webnovel.persistence.VolumePurchase;
import com.example.webnovel.persistence.VolumePurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VolumePurchaseService {
    @Autowired
    private VolumePurchaseRepository volumePurchaseRepository;

    public VolumePurchase purchase(UUID novelId, UUID volumeId, UUID userId) throws PurchaseExistingException {
        Optional<VolumePurchase> existingPurchase = volumePurchaseRepository.findByVolumeIdAndUserId(volumeId, userId);
        if (!existingPurchase.isPresent()) {
            return volumePurchaseRepository.save(new VolumePurchase(novelId, volumeId, userId));
        } else {
            throw new PurchaseExistingException();
        }

    }
}
