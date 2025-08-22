package com.rlevi.encurtador_url.repository;

import java.util.Optional;

import com.rlevi.encurtador_url.model.Url;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);
}
