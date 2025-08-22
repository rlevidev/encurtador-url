package com.rlevi.encurtador_url.service;

import java.time.LocalDateTime;
import java.util.Random;

import com.rlevi.encurtador_url.model.Url;
import com.rlevi.encurtador_url.repository.UrlRepository;

import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final Random random = new Random();
    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public UrlService(UrlRepository repository) {
        this.urlRepository = repository;
    }

    public String shotUrl(String originalUrl) {
        String shortUrl = generateCode();
        Url url = Url.builder()
                .shortUrl(shortUrl)
                .originalUrl(originalUrl)
                .dataExpiracao(LocalDateTime.now().plusDays(7))
                .build();
        urlRepository.save(url);
        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .filter(url -> url.getDataExpiracao().isAfter(LocalDateTime.now()))
                .map(Url::getOriginalUrl)
                .orElse(null);
    }

    public String generateCode() {
        int length = 5 + random.nextInt(6);
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(ALPHANUM.charAt(random.nextInt(ALPHANUM.length())));
        }
        return code.toString();
    }
}