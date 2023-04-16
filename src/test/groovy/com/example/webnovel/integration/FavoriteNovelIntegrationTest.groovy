package com.example.webnovel.integration

import com.example.webnovel.WebNovelApplication
import com.example.webnovel.persistence.*
import com.example.webnovel.service.FavoriteNovelDto
import com.example.webnovel.service.FavoriteNovelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(classes = WebNovelApplication.class)
@ActiveProfiles("test")
class FavoriteNovelIntegrationTest extends Specification {

    @Autowired
    NovelRepository novelRepository

    @Autowired
    UserRepository userRepository

    @Autowired
    FavoriteNovelRepository favoriteNovelRepository

    @Autowired
    FavoriteNovelService favoriteNovelService

    def "test get favorite novels"() {
        given: "Prepare test data"
        Novel novel = new Novel("title", "author", "desc", "genre", 500)
        novel = novelRepository.saveAndFlush(novel)
        User user = new User("test user", 10000L)
        userRepository.saveAndFlush(user)
        and: "add to favorite"
        FavoriteNovel favoriteNovel = favoriteNovelService.addToFavorite(novel.getId(), user.getId())

        when: "get favorite list called"
        List<FavoriteNovelDto> favoriteNovelDtoList = favoriteNovelService.getFavoriteNovels(user.getId())

        then: "read page and total pages included"
        def firstFavoriteNovelDto = favoriteNovelDtoList.first()
        firstFavoriteNovelDto.getNovelId() == novel.getId()
        firstFavoriteNovelDto.getNovelTitle() == novel.getTitle()
        firstFavoriteNovelDto.getTotalPages() == novel.getTotalPages()
        firstFavoriteNovelDto.getReadPage() == favoriteNovel.getReadPage()
    }

}
