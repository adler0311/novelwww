package com.example.webnovel.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/health")
    String health_check() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<NovelResponse> createNovel(@RequestBody CreateNovelRequest novelRequest) {
        UUID novelId = novelService.createNovel(novelRequest.getTitle(), novelRequest.getAuthor(),
                novelRequest.getDescription(), novelRequest.getGenre());
        NovelResponse novelResponse = new NovelResponse(novelId);

        return new ResponseEntity<>(novelResponse, HttpStatus.CREATED);
    }
}
