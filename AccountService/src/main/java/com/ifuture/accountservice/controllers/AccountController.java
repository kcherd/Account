package com.ifuture.accountservice.controllers;

import com.ifuture.accountservice.services.AccountService;
import com.ifuture.accountservice.models.AmountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/getAmount", produces = "application/json")
    public ResponseEntity<String> getAmount(@RequestParam("id") Integer id) {
        if (id == null || id < 0) {
            return ResponseEntity.badRequest().body("Некорректный идентификатор");
        }
        return ResponseEntity.ok("Amount:" + accountService.getAmount(id));
    }

    @PostMapping(value = "/addAmount", produces = "application/json")
    public ResponseEntity<String> addAmount(@RequestBody AmountDto request) {
        if (request.getId() < 0) {
            return ResponseEntity.badRequest().body("Некорректный идентификатор");
        }
        if (request.getValue() < 0) {
            return ResponseEntity.badRequest().body("Некорректное значение");
        }
        accountService.addAmount(request.getId(), request.getValue());
        return ResponseEntity.ok("Done");
    }

    @GetMapping(value = "/resetStats", produces = "application/json")
    public ResponseEntity<String> resetStats() {
        accountService.resetStats();
        return ResponseEntity.ok("Stats cleared");
    }
}
