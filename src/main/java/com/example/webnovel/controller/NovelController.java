package com.example.webnovel.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webnovel.controller.schema.CreateNovelRequest;
import com.example.webnovel.controller.schema.NovelResponse;
import com.example.webnovel.service.NovelService;

@RestController
@RequestMapping("/api/novels")
public class NovelController {

    private final NovelService novelService;

    public NovelController(NovelService novelService) {
        this.novelService = novelService;
    }

    @PostMapping
    public ResponseEntity<NovelResponse> createNovel(@Valid @RequestBody CreateNovelRequest novelRequest) {
        Long novelId = novelService.createNovel(novelRequest.getTitle(), novelRequest.getAuthor(),
                novelRequest.getDescription(), novelRequest.getGenre());
        NovelResponse novelResponse = new NovelResponse(novelId);

        return new ResponseEntity<>(novelResponse, HttpStatus.CREATED);
    }

}
