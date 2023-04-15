package com.example.webnovel.controller.schema;

import com.example.webnovel.persistence.Novel;

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
