package com.rlevi.encurtador_url.controller;

import java.util.Map;

import com.rlevi.encurtador_url.service.UrlService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class UrlController {
    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> shortUrl(@RequestBody Map<String, String> body) {
        String originalUrl = body.get("url");
        String shortUrl = service.shotUrl(originalUrl);
        return ResponseEntity.ok(Map.of("url", "http://localhost:8080/" + shortUrl));
    }

    @GetMapping("/{shortUrl}")
    public Object redirect(@PathVariable String shortUrl) {
        String originalUrl = service.getOriginalUrl(shortUrl);
        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return new RedirectView(originalUrl);
    }
}
