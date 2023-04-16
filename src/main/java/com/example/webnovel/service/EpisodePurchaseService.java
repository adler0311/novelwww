package com.example.webnovel.service;

import com.example.webnovel.persistence.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EpisodePurchaseService {

    private final EpisodePurchaseRepository episodePurchaseRepository;
    private final UserRepository userRepository;
    private final EpisodeRepository episodeRepository;



    public EpisodePurchaseService(EpisodePurchaseRepository episodePurchaseRepository, UserRepository userRepository, EpisodeRepository episodeRepository) {
        this.episodePurchaseRepository = episodePurchaseRepository;
        this.userRepository = userRepository;
        this.episodeRepository = episodeRepository;
    }

    @Transactional
    public EpisodePurchase purchase(Long episodeId, Long userId) throws PointNotEnoughException {
        Optional<User> purchaseUserOptional = userRepository.findById(userId);
        Optional<Episode> episodeToPurchaseOptional = episodeRepository.findById(episodeId);
        if (!purchaseUserOptional.isPresent()) {
            throw new ObjectNotFoundException(userId, User.class.getName());
        }
        if (!episodeToPurchaseOptional.isPresent()) {
            throw new ObjectNotFoundException(episodeId, Episode.class.getName());
        }
        User purchaseUser = purchaseUserOptional.get();
        Episode episodeToPurchase = episodeToPurchaseOptional.get();
        purchaseUser.usePoint(episodeToPurchase.getPointForPurchase());
        userRepository.save(purchaseUser);

        try {
            return episodePurchaseRepository.save(new EpisodePurchase(episodeToPurchase, purchaseUser));
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("This user has already purchased this episode.");
        }
    }
}
