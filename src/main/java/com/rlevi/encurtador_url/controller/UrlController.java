package com.rlevi.encurtador_url.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.rlevi.encurtador_url.dto.ShortUrlRequest;
import com.rlevi.encurtador_url.dto.ShortUrlResponse;
import com.rlevi.encurtador_url.service.UrlService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class UrlController {
    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ShortUrlResponse> shortUrl(@Valid @RequestBody ShortUrlRequest request) {
        String shortUrl = service.shortUrl(request.url());
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/")
                .toUriString();
        return ResponseEntity.ok(new ShortUrlResponse(baseUrl + shortUrl));
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
