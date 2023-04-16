package com.example.webnovel.service

import com.example.webnovel.persistence.*
import org.mockito.ArgumentMatchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.time.LocalDateTime

import static org.mockito.Mockito.when

@SpringBootTest
@ActiveProfiles("test")
class VolumePurchaseServiceTest extends Specification {

    @Autowired
    VolumePurchaseService volumePurchaseService

    @MockBean
    VolumePurchaseRepository volumePurchaseRepository

    @MockBean
    VolumeRepository volumeRepository

    @MockBean
    UserRepository userRepository

    @Autowired
    NovelRepository novelRepository


    def "purchaseVolume should save volume payment and decrease user point"() {
        given: "prepare newPurchase"
        Novel novel = new Novel("title", "author", "desc", "genre", 100)
        Volume volumeToPurchase = new Volume("series 1", LocalDateTime.now(), 1, 250, 5_000_000L, 100L)
        volumeToPurchase.setNovel(novel)
        User aUser = new User("test user", 10000L)
        VolumePurchase newPurchase = new VolumePurchase(volumeToPurchase, aUser)

        and: "mock volumeRepository method"
        when(volumeRepository.getReferenceById(volumeToPurchase.getId())).thenReturn(volumeToPurchase)

        and: "mock userRepository method"
        when(userRepository.getReferenceById(aUser.getId())).thenReturn(aUser)

        and: "mock volumePurchaseRepository methods"
        when(volumePurchaseRepository.findByVolumeIdAndUserId(volumeToPurchase.getId(), aUser.getId())).thenReturn(Optional.empty())
        when(volumePurchaseRepository.save(ArgumentMatchers.any(VolumePurchase.class))).thenReturn(newPurchase)

        when: "purchase is called"
        VolumePurchase savedPurchase = volumePurchaseService.purchase(volumeToPurchase.getId(), aUser.getId())

        then: "The new purchase is saved and returned"
        savedPurchase == newPurchase
        aUser.getPoint() == 9900L
    }
}
