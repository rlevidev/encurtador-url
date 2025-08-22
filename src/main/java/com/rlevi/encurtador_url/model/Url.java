package com.rlevi.encurtador_url.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url-original", nullable = false, unique = true)
    private String originalUrl;
    @Column(name = "short-url", nullable = false, unique = true, length = 10)
    private String shortUrl;
    @Column(nullable = false)
    private LocalDateTime dataExpiracao;
}
