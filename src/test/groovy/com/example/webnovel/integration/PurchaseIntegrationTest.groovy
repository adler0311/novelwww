package com.example.webnovel.integration

import com.example.webnovel.WebNovelApplication
import com.example.webnovel.novel.persistence.Novel
import com.example.webnovel.novel.persistence.NovelRepository
import com.example.webnovel.user.persistence.User
import com.example.webnovel.user.persistence.UserRepository
import com.example.webnovel.episode.persistence.Episode
import com.example.webnovel.transaction.persistence.EpisodePurchaseRepository
import com.example.webnovel.episode.persistence.EpisodeRepository
import com.example.webnovel.transaction.service.EpisodePurchaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.time.LocalDateTime

@SpringBootTest(classes = WebNovelApplication.class)
@ActiveProfiles("test")
class PurchaseIntegrationTest extends Specification {

    @Autowired
    NovelRepository novelRepository

    @Autowired
    UserRepository userRepository

    @Autowired
    EpisodeRepository episodeRepository

    @Autowired
    EpisodePurchaseRepository episodePurchaseRepository

    @Autowired
    EpisodePurchaseService episodePurchaseService

    def "testConcurrentPurchase"() {
        given: "Prepare test data"
        Novel novel = new Novel("title", "author", "desc", "genre", 5599)
        novel = novelRepository.saveAndFlush(novel)
        Episode episode = new Episode("series 1", LocalDateTime.now(), 1, 250, 5_000_000L, 100L, 100)
        episode.setNovel(novel)
        episodeRepository.saveAndFlush(episode)
        User user = new User("test user", 10000L)
        userRepository.saveAndFlush(user)

        when: "Perform concurrent purchases"
        int numConcurrentPurchases = 3
        ExecutorService executorService = Executors.newFixedThreadPool(numConcurrentPurchases)
        CountDownLatch latch = new CountDownLatch(numConcurrentPurchases)

        (0..<numConcurrentPurchases).each { _ ->
            executorService.execute {
                try {
                    episodePurchaseService.purchase(episode.getId(), user.getId())
                } catch (ignored) {
                    System.out.println("integrity error occurred")
                } finally {
                    latch.countDown()
                }
            }
        }

        then: "Wait for all tasks to complete and assert the number of exceptions"
        latch.await()
        executorService.shutdown()

        and: "Assert episodePurchase created only once"
        def createdEpisodePurchases = episodePurchaseRepository.findAll()
        createdEpisodePurchases.size() == 1

        and: "Assert user point decreased only once"
        User purchaseUser = userRepository.findById(user.getId()).get()
        purchaseUser.getPoint() == 9900L
    }


    def "testConcurrentPurchaseDifferentEpisodes"() {
        given: "Prepare test data"
        Novel novel = new Novel("title", "author", "desc", "genre", 5599)
        novel = novelRepository.saveAndFlush(novel)
        Episode episode1 = new Episode("series 1", LocalDateTime.now(), 1, 250, 5_000_000L, 100L, 100)
        Episode episode2 = new Episode("series 2", LocalDateTime.now(), 2, 250, 5_000_000L, 100L, 100)
        Episode episode3 = new Episode("series 3", LocalDateTime.now(), 3, 250, 5_000_000L, 100L, 100)

        episode1.setNovel(novel)
        episode2.setNovel(novel)
        episode3.setNovel(novel)

        List<Episode> episodes = Arrays.asList(episode1, episode2, episode3)
        episodeRepository.saveAllAndFlush(episodes)

        User user = new User("test user", 10000L)
        userRepository.saveAndFlush(user)

        when: "Perform concurrent purchases"
        int numConcurrentPurchases = 3
        ExecutorService executorService = Executors.newFixedThreadPool(numConcurrentPurchases)
        CountDownLatch latch = new CountDownLatch(numConcurrentPurchases)

        (0..<numConcurrentPurchases).each { i ->
            executorService.execute {
                try {
                    episodePurchaseService.purchase(episodes.get(i).getId(), user.getId())
                } catch (e) {
                    System.out.println(e)
//                    System.out.println("integrity error occurred")
                } finally {
                    latch.countDown()
                }
            }
        }

        then: "Wait for all tasks to complete and assert the number of exceptions"
        latch.await()
        executorService.shutdown()

        and: "Assert episodePurchase created only once"
        def createdEpisodePurchases = episodePurchaseRepository.findAll()
        createdEpisodePurchases.size() == 3

        and: "Assert user point decreased only once"
        User purchaseUser = userRepository.findById(user.getId()).get()
        purchaseUser.getPoint() == 9700L
    }

}
