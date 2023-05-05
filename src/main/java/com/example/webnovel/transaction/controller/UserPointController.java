package com.example.webnovel.transaction.controller;

import com.example.webnovel.transaction.dto.UserPointChargeRequest;
import com.example.webnovel.transaction.dto.UserPointChargeResponse;
import com.example.webnovel.transaction.service.UserPointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users/{userId}/points")
public class UserPointController {
    private final UserPointService userPointService;

    public UserPointController(UserPointService userPointService) {
        this.userPointService = userPointService;
    }

    @PostMapping
    public ResponseEntity<UserPointChargeResponse> chargeUserPoint(
            @PathVariable Long userId,
            @Valid @RequestBody UserPointChargeRequest userPointChargeRequest) {
        UserPointChargeResponse userPointChargeResponse = userPointService.chargePoints(userId, userPointChargeRequest.points);
        return new ResponseEntity<>(userPointChargeResponse, HttpStatus.CREATED);
    }
}
