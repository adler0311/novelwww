package com.example.webnovel.transaction.service;

import com.example.webnovel.episode.persistence.Episode;
import com.example.webnovel.episode.persistence.EpisodeRead;
import com.example.webnovel.episode.persistence.EpisodeReadRepository;
import com.example.webnovel.episode.persistence.EpisodeRepository;
import com.example.webnovel.transaction.persistence.EpisodePurchase;
import com.example.webnovel.transaction.persistence.EpisodePurchaseRepository;
import com.example.webnovel.transaction.persistence.PointNotEnoughException;
import com.example.webnovel.user.persistence.User;
import com.example.webnovel.user.persistence.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EpisodePurchaseService {

    private final EpisodePurchaseRepository episodePurchaseRepository;
    private final UserRepository userRepository;
    private final EpisodeRepository episodeRepository;

    private final EpisodeReadRepository episodeReadRepository;



    public EpisodePurchaseService(EpisodePurchaseRepository episodePurchaseRepository, UserRepository userRepository, EpisodeRepository episodeRepository, EpisodeReadRepository episodeReadRepository) {
        this.episodePurchaseRepository = episodePurchaseRepository;
        this.userRepository = userRepository;
        this.episodeRepository = episodeRepository;
        this.episodeReadRepository = episodeReadRepository;
    }

    @Retryable(include = {ObjectOptimisticLockingFailureException.class})
    @Transactional
    public EpisodePurchase purchase(Long episodeId, Long userId) throws PointNotEnoughException {
        Optional<User> purchaseUserOptional = userRepository.findUserById(userId);
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
            EpisodePurchase episodePurchase = episodePurchaseRepository.save(new EpisodePurchase(episodeToPurchase, purchaseUser));
            episodeReadRepository.save(new EpisodeRead(purchaseUser, episodeToPurchase));
            return episodePurchase;
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("This user has already purchased this episode.");
        }
    }
}
