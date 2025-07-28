package ru.job4j.urlshortcut.controller.redirect;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.controller.converter.convert.SiteConverter;
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

    @GetMapping("/redirect/{encodedUrl}")
    public ResponseEntity<Void> redirect(@Valid
                                         @NotBlank(message = "секретный код не может быть пустым")
                                         @PathVariable String encodedUrl,
                                         @AuthenticationPrincipal UserDetailsImpl user)
            throws Exception {
        var decodedUrl = SiteConverter.decrypt(encodedUrl);

        webSecurityConfig.allowedUrls.add(decodedUrl);

        var userFromRepository = userRepository.findUserById(user.getId()).get();

        var calledUrl = new CalledUrl(userFromRepository, decodedUrl);

        calledUrlRepository.saveOrUpdate(calledUrl);

        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create(decodedUrl))
                .build();
    }
}
