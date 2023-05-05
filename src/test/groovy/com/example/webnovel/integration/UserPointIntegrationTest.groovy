package com.example.webnovel.integration

import com.example.webnovel.WebNovelApplication
import com.example.webnovel.persistence.Episode
import com.example.webnovel.persistence.EpisodePurchaseRepository
import com.example.webnovel.persistence.EpisodeRepository
import com.example.webnovel.persistence.Novel
import com.example.webnovel.persistence.NovelRepository
import com.example.webnovel.persistence.User
import com.example.webnovel.persistence.UserRepository
import com.example.webnovel.service.EpisodePurchaseService
import com.example.webnovel.service.EpisodePurchaseServiceTest
import com.example.webnovel.transaction.service.UserPointService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.time.LocalDateTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SpringBootTest(classes = WebNovelApplication.class)
@ActiveProfiles("test")
class UserPointIntegrationTest extends Specification {
    @Autowired
    NovelRepository novelRepository

    @Autowired
    UserRepository userRepository

    @Autowired
    EpisodeRepository episodeRepository

    @Autowired
    EpisodePurchaseRepository episodePurchaseRepository


    @Autowired
    UserPointService userPointService

    @Autowired
    EpisodePurchaseService episodePurchaseService

    def "requests for point charging and episode purchasing come in simultaneously, normal case"() {
        given:
        Novel novel = new Novel("title", "author", "desc", "genre", 5599)
        novel = novelRepository.saveAndFlush(novel)
        Episode episode = new Episode("series 1", LocalDateTime.now(), 1, 250, 5_000_000L, 300L, 100)
        episode.setNovel(novel)
        episodeRepository.saveAndFlush(episode)
        User user = new User("test user", 1000L)
        userRepository.saveAndFlush(user)
        var pointToCharge = 100

        when:
        int numConcurrentRequests = 2
        ExecutorService executorService = Executors.newFixedThreadPool(numConcurrentRequests)
        CountDownLatch latch = new CountDownLatch(numConcurrentRequests)

        (0..<numConcurrentRequests).each { i ->
            executorService.execute {
                try {
                    if (i < 1) {
                        userPointService.chargePoints(user.id, pointToCharge)
                    } else {
                        episodePurchaseService.purchase(episode.id, user.id)
                    }

                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()
        executorService.shutdown()

        then:
        User theUser = userRepository.findById(user.getId()).get()
        theUser.getPoint() == 800L
    }
}
