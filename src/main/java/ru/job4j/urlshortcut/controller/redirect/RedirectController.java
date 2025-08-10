package ru.job4j.urlshortcut.controller.redirect;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.siteutils.SiteConverter;
import ru.job4j.urlshortcut.model.CalledUrl;
import ru.job4j.urlshortcut.repository.calledurl.CalledUrlRepository;
import ru.job4j.urlshortcut.repository.user.UserRepository;
import ru.job4j.urlshortcut.securityconfig.WebSecurityConfig;
import ru.job4j.urlshortcut.userdetails.UserDetailsImpl;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class RedirectController {

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    private CalledUrlRepository calledUrlRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SiteConverter siteConverter;

    @GetMapping("/redirect/{encryptedUrl}")
    public ResponseEntity<Void> redirect(@Valid
                                         @NotBlank(message = "секретный код не может быть пустым")
                                         @PathVariable String encryptedUrl,
                                         @AuthenticationPrincipal UserDetailsImpl user)
            throws Exception {

        var decodedUrl = siteConverter.decrypt(encryptedUrl);

        webSecurityConfig.allowedUrls.add(decodedUrl);

        var userFromRepository = userRepository.findUserById(user.getId()).get();

        var calledUrl = new CalledUrl(userFromRepository, decodedUrl);

        if (calledUrlRepository.existsByUrl(calledUrl.getUrl())) {
            calledUrlRepository.updateByUrl(calledUrl.getUrl());
        } else {
            calledUrlRepository.save(calledUrl);
        }

        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create(decodedUrl))
                .build();
    }
}
