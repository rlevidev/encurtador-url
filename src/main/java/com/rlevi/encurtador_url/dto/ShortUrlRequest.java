package com.rlevi.encurtador_url.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record ShortUrlRequest(
        @NotBlank(message = "A URL não pode está vazia") @URL(message = "A URL fornecida não é válida") String url) {
}
