package com.example.parkingmanager.controller;

import com.example.parkingmanager.dto.WebhookEventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {

    @PostMapping({"/simulate", "/webhook"})
    public ResponseEntity<String> handleWebhook(@RequestBody WebhookEventDto event) {

        System.out.println("EVENT RECEIVED FROM THE SIMULATOR: ");
        System.out.println(event);

        return ResponseEntity.ok("Webhook successfully received");
    }
}
