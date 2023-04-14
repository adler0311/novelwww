package com.example.webnovel.service
import spock.util.concurrent.AsyncConditions

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


import com.example.webnovel.persistence.Novel
import com.example.webnovel.persistence.NovelRepository
import com.example.webnovel.persistence.User
import com.example.webnovel.persistence.UserRepository
import com.example.webnovel.persistence.Volume
import com.example.webnovel.persistence.VolumePurchase
import com.example.webnovel.persistence.VolumePurchaseRepository
import com.example.webnovel.persistence.VolumeRepository
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import org.mockito.Mockito
import spock.util.concurrent.AsyncConditions

import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicInteger

import static org.mockito.Mockito.when


@SpringBootTest
@ActiveProfiles("test")
class VolumePurchaseServiceTest extends Specification {

    @Autowired
    VolumePurchaseService volumePurchaseService;

    @MockBean
    VolumePurchaseRepository volumePurchaseRepository;

    @MockBean
    VolumeRepository volumeRepository;

    @MockBean
    UserRepository userRepository;

    @Autowired
    NovelRepository novelRepository;


    def "purchaseVolume should save volume payment and decrease user point"() {
        given: "prepare newPurchase"
        Novel novel = new Novel("title", "author", "desc", "genre")
        Volume volumeToPurchase = new Volume("series 1", LocalDateTime.now(), 1, 250, 5_000_000L, 100L)
        volumeToPurchase.setNovel(novel);
        User aUser = new User("test user", 10000L)
        VolumePurchase newPurchase = new VolumePurchase(novel.getId(), volumeToPurchase.getId(), aUser.getId())

        and: "mock volumeRepository method"
        when(volumeRepository.getReferenceById(volumeToPurchase.getId())).thenReturn(volumeToPurchase)

        and: "mock userRepository method"
        when(userRepository.getReferenceById(aUser.getId())).thenReturn(aUser);

        and: "mock volumePurchaseRepository methods"
        when(volumePurchaseRepository.findByVolumeIdAndUserId(volumeToPurchase.getId(), aUser.getId())).thenReturn(Optional.empty())
        when(volumePurchaseRepository.save(ArgumentMatchers.any(VolumePurchase.class))).thenReturn(newPurchase)

        when: "purchase is called"
        VolumePurchase savedPurchase = volumePurchaseService.purchase(novel.getId(), volumeToPurchase.getId(), aUser.getId())

        then: "The new purchase is saved and returned"
        savedPurchase == newPurchase
        aUser.getPoint() == 9900L
    }


}
