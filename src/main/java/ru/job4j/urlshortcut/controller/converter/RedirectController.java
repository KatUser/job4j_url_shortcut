package ru.job4j.urlshortcut.controller.converter;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.repository.user.UserRepository;
import ru.job4j.urlshortcut.securityconfig.WebSecurityConfig;
import ru.job4j.urlshortcut.service.user.UserService;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class RedirectController {

    private WebSecurityConfig webSecurityConfig;

    private UserRepository userRepository;

    private UserService userService;

    @GetMapping("/redirect/{encodedUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String encodedUrl) {
        var decodedUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8);
        webSecurityConfig.setAllowedExternalUrl(decodedUrl);
        User user = userService.findBySite(decodedUrl).get();
        userRepository.saveOrUpdate(user);
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create(decodedUrl))
                .build();
    }
}
