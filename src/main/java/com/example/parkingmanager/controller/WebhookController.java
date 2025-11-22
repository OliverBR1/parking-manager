package com.example.parkingmanager.controller;

import com.example.parkingmanager.dto.WebhookEventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody WebhookEventDto event) {

        System.out.println("🔥 EVENTO RECEBIDO DO SIMULADOR:");
        System.out.println(event);

        // Aqui você chama suas regras, serviços etc.
        return ResponseEntity.ok("Webhook recebido com sucesso");
    }
}
