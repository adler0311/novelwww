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
class EpisodePurchaseServiceTest extends Specification {

    @Autowired
    EpisodePurchaseService episodePurchaseService

    @MockBean
    EpisodePurchaseRepository episodePurchaseRepository

    @MockBean
    EpisodeRepository episodeRepository

    @MockBean
    UserRepository userRepository

    @Autowired
    NovelRepository novelRepository


    def "purchaseEpisode should save episode payment and decrease user point"() {
        given: "prepare newPurchase"
        Novel novel = new Novel("title", "author", "desc", "genre", 100)
        Episode episodeToPurchase = new Episode("series 1", LocalDateTime.now(), 1, 250, 5_000_000L, 100L)
        episodeToPurchase.setNovel(novel)
        User aUser = new User("test user", 10000L)
        EpisodePurchase newPurchase = new EpisodePurchase(episodeToPurchase, aUser)

        and: "mock episodeRepository method"
        when(episodeRepository.getReferenceById(episodeToPurchase.getId())).thenReturn(episodeToPurchase)

        and: "mock userRepository method"
        when(userRepository.getReferenceById(aUser.getId())).thenReturn(aUser)

        and: "mock episodePurchaseRepository methods"
        when(episodePurchaseRepository.findByEpisodeIdAndUserId(episodeToPurchase.getId(), aUser.getId())).thenReturn(Optional.empty())
        when(episodePurchaseRepository.save(ArgumentMatchers.any(EpisodePurchase.class))).thenReturn(newPurchase)

        when: "purchase is called"
        EpisodePurchase savedPurchase = episodePurchaseService.purchase(episodeToPurchase.getId(), aUser.getId())

        then: "The new purchase is saved and returned"
        savedPurchase == newPurchase
        aUser.getPoint() == 9900L
    }
}
