package com.example.webnovel.service;

import com.example.webnovel.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VolumePurchaseService {

    private final VolumePurchaseRepository volumePurchaseRepository;
    private final UserRepository userRepository;
    private final VolumeRepository volumeRepository;



    public VolumePurchaseService(VolumePurchaseRepository volumePurchaseRepository, UserRepository userRepository, VolumeRepository volumeRepository) {
        this.volumePurchaseRepository = volumePurchaseRepository;
        this.userRepository = userRepository;
        this.volumeRepository = volumeRepository;
    }

    public VolumePurchase purchase(UUID novelId, UUID volumeId, UUID userId) throws PurchaseExistingException, PointNotEnoughException {
        Optional<VolumePurchase> existingPurchase = volumePurchaseRepository.findByVolumeIdAndUserId(volumeId, userId);

        if (existingPurchase.isPresent()) {
            throw new PurchaseExistingException();
        }

        User purchaseUser = userRepository.getReferenceById(userId);
        Volume volumeToPurchase = volumeRepository.getReferenceById(volumeId);
        purchaseUser.usePoint(volumeToPurchase.getPointForPurchase());
        userRepository.save(purchaseUser);
        return volumePurchaseRepository.save(new VolumePurchase(novelId, volumeId, userId));
    }
}
