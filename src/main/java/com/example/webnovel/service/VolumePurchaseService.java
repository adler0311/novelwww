package com.example.webnovel.service;

import com.example.webnovel.persistence.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public VolumePurchase purchase(Long volumeId, Long userId) throws PointNotEnoughException {
        Optional<User> purchaseUserOptional = userRepository.findById(userId);
        Optional<Volume> volumeToPurchaseOptional = volumeRepository.findById(volumeId);
        if (!purchaseUserOptional.isPresent()) {
            throw new ObjectNotFoundException(userId, User.class.getName());
        }
        if (!volumeToPurchaseOptional.isPresent()) {
            throw new ObjectNotFoundException(volumeId, Volume.class.getName());
        }
        User purchaseUser = purchaseUserOptional.get();
        Volume volumeToPurchase = volumeToPurchaseOptional.get();
        purchaseUser.usePoint(volumeToPurchase.getPointForPurchase());
        userRepository.save(purchaseUser);

        try {
            return volumePurchaseRepository.save(new VolumePurchase(volumeToPurchase, purchaseUser));
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("This user has already purchased this volume.");
        }
    }
}
