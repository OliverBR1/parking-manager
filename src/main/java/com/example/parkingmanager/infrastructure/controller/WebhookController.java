package com.example.parkingmanager.infrastructure.controller;

import com.example.parkingmanager.application.dto.WebhookEventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody WebhookEventDto event) {

        System.out.println("EVENT RECEIVED FROM THE WEBHOOK: ");
        System.out.println(event);

        return ResponseEntity.ok("Webhook successfully received");
    }
}
