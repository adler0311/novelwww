package com.example.webnovel.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.example.webnovel.controller.schema.NovelListResponse;
import com.example.webnovel.persistence.Novel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                novelRequest.getDescription(), novelRequest.getGenre(), novelRequest.getTotalPage());
        NovelResponse novelResponse = new NovelResponse(novelId);

        return new ResponseEntity<>(novelResponse, HttpStatus.CREATED);
    }


    @GetMapping("/best-sellers")
    public ResponseEntity<NovelListResponse> getBestSellers() {
        List<Novel> novels = novelService.getBestSellers();

        NovelListResponse novelListResponse = new NovelListResponse(novels);
        return new ResponseEntity<>(novelListResponse, HttpStatus.OK);

    }

}
