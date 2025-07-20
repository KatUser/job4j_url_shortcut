package ru.job4j.urlshortcut.controller.converter;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.securityconfig.WebSecurityConfig;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class RedirectController {

    private WebSecurityConfig webSecurityConfig;

    @GetMapping("/redirect/{encodedUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String encodedUrl) {
        var decodedUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8);
        webSecurityConfig.setAllowedExternalUrl(decodedUrl);
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create(decodedUrl))
                .build();
    }
}
