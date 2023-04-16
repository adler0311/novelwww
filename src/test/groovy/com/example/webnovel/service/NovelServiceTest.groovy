package com.example.webnovel.service

import com.example.webnovel.persistence.FavoriteNovelRepository
import com.example.webnovel.persistence.Novel
import com.example.webnovel.persistence.NovelRepository
import com.example.webnovel.persistence.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification


import static org.mockito.Mockito.when

@SpringBootTest
@ActiveProfiles("test")
class NovelServiceTest extends Specification {
    @Autowired
    NovelService novelService;

    @MockBean
    NovelRepository novelRepository;

//    def "get bestselling novel list"() {
//        given: "prepare novels"
//        List<Novel> novels = []
//        def random = new Random();
//        for (int i = 1; i <= 10; i++) {
//            Novel novel = new Novel("title ${i}", "author", "desc ${i}", "fantasy", 100)
//            novel.setPurchaseCount(random.nextInt(1001))
//            novels << novel
//        }
//
//        and: "mock novelRepository return novels by purchase count"
//        when(novelRepository.getBestSellers()).thenReturn(novels)
//
//        when: "get bestseller called"
//        def bestSellers = novelService.getBestSellers()
//
//        then: "best seller novel list returned"
//        novels == bestSellers
//    }

    def "updateBestsellerNovelCache update cached data"() {
        given: "cache best seller data"
        List<Novel> novels = []
        def random = new Random();
        for (int i = 1; i <= 2; i++) {
            Novel novel = new Novel("title ${i}", "author", "desc ${i}", "fantasy", 100)
            novel.setPurchaseCount(random.nextInt(1001))
            novels << novel
        }
        when(novelRepository.getBestSellers()).thenReturn(novels)
        novelService.getBestSellers()

        when: "cache put data"
        List<Novel> updatedNovels = []
        for (int i = 1; i <= 2; i++) {
            Novel novel = new Novel("title ${i}", "author", "desc ${i}", "fantasy", 100)
            novel.setPurchaseCount(random.nextInt(1001))
            updatedNovels << novel
        }
        when(novelRepository.getBestSellers()).thenReturn(updatedNovels)
        novelService.updateBestsellerNovelCache()

        then: "cached best seller updated"
        novelService.getBestSellers() == updatedNovels
    }
}
