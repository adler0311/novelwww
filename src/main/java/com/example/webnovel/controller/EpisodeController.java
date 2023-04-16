package com.example.webnovel.controller;

import com.example.webnovel.controller.schema.EpisodePurchaseRequest;
import com.example.webnovel.controller.schema.EpisodePurchaseResponse;
import com.example.webnovel.persistence.PointNotEnoughException;
import com.example.webnovel.persistence.EpisodePurchase;
import com.example.webnovel.service.EpisodePurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/novels/{novelId}/episodes")
public class EpisodeController {

    private final EpisodePurchaseService episodePurchaseService;

    public EpisodeController(EpisodePurchaseService episodePurchaseService) { this.episodePurchaseService = episodePurchaseService; }

    @PostMapping("/{episodeId}")
    public ResponseEntity<EpisodePurchaseResponse> purchaseEpisode(
            @RequestBody EpisodePurchaseRequest episodePurchaseRequest,
            @PathVariable Long novelId,
            @PathVariable Long episodeId
    ) {
        try {
            EpisodePurchase episodePurchase = episodePurchaseService.purchase(episodeId, episodePurchaseRequest.getUserId());
            return new ResponseEntity<>(new EpisodePurchaseResponse(episodePurchase.getId()), HttpStatus.OK);
        } catch (PointNotEnoughException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
