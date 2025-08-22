package com.rlevi.encurtador_url.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url-original", nullable = false, unique = true)
    private String url;
    @Column(name = "short-url", nullable = false, unique = true, length = 10)
    private String shortUrl;
    @Column(nullable = false)
    private LocalDateTime dataExpiracao;
}
