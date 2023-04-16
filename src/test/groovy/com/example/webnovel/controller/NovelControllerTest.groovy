package com.example.webnovel.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import com.example.webnovel.service.NovelService
import com.example.webnovel.controller.schema.CreateNovelRequest
import com.example.webnovel.controller.NovelController

@WebMvcTest(NovelController)
class NovelControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    @MockBean
    private NovelService novelService

    def "Create novel successfully"() {
        given: "A valid novel request"
        CreateNovelRequest novelRequest = new CreateNovelRequest(title: "소설 제목", author: "작가 이름", description: "소설 설명", genre: "소설 장르", totalPage: 456)

        and: "The service returns a valid novelId"
        Long novelId = 1L;
        when(novelService.createNovel(novelRequest.getTitle(), novelRequest.getAuthor(), novelRequest.getDescription(), novelRequest.getGenre(), novelRequest.getTotalPage())).thenReturn(novelId)

        when: "The API is called"
        def requestBody = '{"title":"소설 제목","author":"작가 이름","description":"소설 설명","genre":"소설 장르", "totalPage": 456}'
        mockMvc.perform(
                post("/api/novels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath('$.message').value("소설이 성공적으로 등록되었습니다."))
                .andExpect(jsonPath('$.novelId').value(novelId.toString()))

        then: "The response contains the expected data"
        true
    }

    def "Create novel with bad request"() {
        when: "The API is called with an invalid request body"
        def requestBody = '{"title":"소설 제목","author":"작가 이름","genre":"소설 장르"}'
        mockMvc.perform(
                post("/api/novels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())

        then: "The response contains a bad request status"
        true
    }
}

