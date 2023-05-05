package com.example.webnovel.novel.dto;

import com.example.webnovel.novel.persistence.Novel;

import java.util.ArrayList;
import java.util.List;

public class NovelListResponse {
    List<NovelListItemResponse> items;

    public NovelListResponse(List<Novel> novels) {
        List<NovelListItemResponse> items = new ArrayList<>();
        for (Novel novel: novels) {
            items.add(new NovelListItemResponse(novel));
        }
        this.items = items;
    }

    public List<NovelListItemResponse> getItems() {
        return items;
    }
}
