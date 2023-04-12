package com.example.webnovel.service

import com.example.webnovel.persistence.VolumePurchase
import com.example.webnovel.persistence.VolumePurchaseRepository
import org.mockito.ArgumentMatchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import org.mockito.Mockito

import static org.mockito.Mockito.when


@SpringBootTest
@ActiveProfiles("test")
class VolumePurchaseServiceTest extends Specification {

    @Autowired
    VolumePurchaseService volumePurchaseService;

    @MockBean
    VolumePurchaseRepository volumePurchaseRepository;

    def "purchaseVolume should save volume payment"() {
        given: "prepare newPurchase"
        UUID novelId = UUID.randomUUID()
        UUID volumeId = UUID.randomUUID()
        UUID userId = UUID.randomUUID()
        VolumePurchase newPurchase = new VolumePurchase(novelId, volumeId, userId)

        and: "mock volumePurchaseRepository methods"
        when(volumePurchaseRepository.findByVolumeIdAndUserId(volumeId, userId)).thenReturn(Optional.empty())
        when(volumePurchaseRepository.save(ArgumentMatchers.any(VolumePurchase.class))).thenReturn(newPurchase)


        when: "purchase is called"
        VolumePurchase savedPurchase = volumePurchaseService.purchase(novelId, volumeId, userId)

        then: "The new purchase is saved and returned"
        assert savedPurchase == newPurchase
        true
    }

    def "purchaseVolume should save volume payment"() {
        given: "prepare newPurchase"
        UUID novelId = UUID.randomUUID()
        UUID volumeId = UUID.randomUUID()
        UUID userId = UUID.randomUUID()
        VolumePurchase newPurchase = new VolumePurchase(novelId, volumeId, userId)

        and: "mock volumePurchaseRepository methods"
        when(volumePurchaseRepository.findByVolumeIdAndUserId(volumeId, userId)).thenReturn(Optional.of(newPurchase))


        when: "purchase is called"
        VolumePurchase existingPurchase = volumePurchaseService.purchase(novelId, volumeId, userId)

        then: "The new purchase is saved and returned"
        assert existingPurchase == newPurchase
        true
    }



}
