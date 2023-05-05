package com.example.webnovel.unit.transaction.service

import com.example.webnovel.episode.persistence.Episode
import com.example.webnovel.episode.persistence.EpisodeRepository
import com.example.webnovel.novel.persistence.Novel
import com.example.webnovel.novel.persistence.NovelRepository
import com.example.webnovel.transaction.persistence.EpisodePurchase
import com.example.webnovel.transaction.persistence.EpisodePurchaseRepository
import com.example.webnovel.transaction.service.EpisodePurchaseService
import com.example.webnovel.user.persistence.User
import com.example.webnovel.user.persistence.UserRepository
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


    def "purchaseEpisode should save episode purchase and decrease user point"() {
        given: "prepare newPurchase"
        Novel novel = new Novel("title", "author", "desc", "genre", 100)
        Episode episodeToPurchase = new Episode("series 1", LocalDateTime.now(), 1, 250, 5_000_000L, 100L, 100)
        episodeToPurchase.setNovel(novel)
        User aUser = new User("test user", 10000L)
        EpisodePurchase newPurchase = new EpisodePurchase(episodeToPurchase, aUser)

        and: "mock episodeRepository method"
        when(episodeRepository.findById(episodeToPurchase.getId())).thenReturn(Optional.of(episodeToPurchase))

        and: "mock userRepository method"
        when(userRepository.findById(aUser.getId())).thenReturn(Optional.of(aUser))

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
