package com.rlevi.encurtador_url.controller;

import java.util.HashMap;
import java.util.Map;

import com.rlevi.encurtador_url.service.UrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UrlController {
    @Autowired
    private UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> shotUrl(@RequestBody Map<String, String> body) {
        String shortUrl = service.shotUrl(originalUrl);
        Map<String, String> response = new HashMap<>();
        response.put("shortUrl", shortUrl);
        return ResponseEntity.ok(response);
    }
}
