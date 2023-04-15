package com.example.webnovel.integration

import com.example.webnovel.WebNovelApplication
import com.example.webnovel.persistence.Novel
import com.example.webnovel.persistence.NovelRepository
import com.example.webnovel.persistence.User
import com.example.webnovel.persistence.UserRepository
import com.example.webnovel.persistence.Volume
import com.example.webnovel.persistence.VolumePurchaseRepository
import com.example.webnovel.persistence.VolumeRepository
import com.example.webnovel.service.PurchaseExistingException
import com.example.webnovel.service.VolumePurchaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.time.LocalDateTime

@SpringBootTest(classes = WebNovelApplication.class)
@ActiveProfiles("test")
class PurchaseIntegrationTest extends Specification {

    @Autowired
    NovelRepository novelRepository

    @Autowired
    UserRepository userRepository

    @Autowired
    VolumeRepository volumeRepository

    @Autowired
    VolumePurchaseRepository volumePurchaseRepository

    @Autowired
    VolumePurchaseService volumePurchaseService

    def "testConcurrentPurchase"() {
        given: "Prepare test data"
        Novel novel = new Novel("title", "author", "desc", "genre")
        novel = novelRepository.saveAndFlush(novel)
        Volume volume = new Volume("series 1", LocalDateTime.now(), 1, 250, 5_000_000L, 100L)
        volume.setNovel(novel)
        volumeRepository.saveAndFlush(volume)
        User user = new User("test user", 10000L)
        userRepository.saveAndFlush(user)

        when: "Perform concurrent purchases"
        int numConcurrentPurchases = 3
        ExecutorService executorService = Executors.newFixedThreadPool(numConcurrentPurchases)
        CountDownLatch latch = new CountDownLatch(numConcurrentPurchases)

        (0..<numConcurrentPurchases).each { _ ->
            executorService.execute {
                volumePurchaseService.purchase(novel.getId(), volume.getId(), user.getId())
                latch.countDown()
            }
        }

        then: "Wait for all tasks to complete and assert the number of exceptions"
        latch.await()
        executorService.shutdown()

        and: "Assert volumePurchase created only once"
        def createdVolumePurchases = volumePurchaseRepository.findAll()
        createdVolumePurchases.size() == 1

        and: "Assert user point decreased only once"
        User purchaseUser = userRepository.findById(user.getId()).get()
        purchaseUser.getPoint() == 9900L
    }


}
