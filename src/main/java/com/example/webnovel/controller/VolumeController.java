package com.example.webnovel.controller;

import com.example.webnovel.controller.schema.PurchaseVolumeRequest;
import com.example.webnovel.controller.schema.VolumePurchaseResponse;
import com.example.webnovel.persistence.VolumePurchase;
import com.example.webnovel.service.PurchaseExistingException;
import com.example.webnovel.service.VolumePurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/novels/{novelId}/volumes")
public class VolumeController {

    private final VolumePurchaseService volumePurchaseService;

    public VolumeController(VolumePurchaseService volumePurchaseService) { this.volumePurchaseService = volumePurchaseService; }

    @PostMapping("/{volumeId}")
    public ResponseEntity<VolumePurchaseResponse> purchaseVolume(
            @RequestBody PurchaseVolumeRequest purchaseVolumeRequest,
            @PathVariable UUID novelId,
            @PathVariable UUID volumeId
    ) {
        VolumePurchase volumePurchase;
        try {
            volumePurchase = volumePurchaseService.purchase(novelId, volumeId, purchaseVolumeRequest.getUserId());
        } catch (PurchaseExistingException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return new ResponseEntity<>(new VolumePurchaseResponse(volumePurchase.getId()), HttpStatus.OK);
    }

}
