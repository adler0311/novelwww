package com.example.webnovel.service;

import com.example.webnovel.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public VolumePurchase purchase(Long novelId, Long volumeId, Long userId) throws PointNotEnoughException {
        User purchaseUser = userRepository.getReferenceById(userId);
        Volume volumeToPurchase = volumeRepository.getReferenceById(volumeId);
        purchaseUser.usePoint(volumeToPurchase.getPointForPurchase());
        userRepository.save(purchaseUser);
        return volumePurchaseRepository.save(new VolumePurchase(novelId, volumeId, userId));
    }
}
