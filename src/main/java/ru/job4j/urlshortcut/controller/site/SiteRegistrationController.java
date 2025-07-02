package ru.job4j.urlshortcut.controller.site;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.generator.CredentialsGenerator;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.site.SiteService;

import java.util.Arrays;

@AllArgsConstructor
@RestController
@RequestMapping("/registration")
public class SiteRegistrationController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private CredentialsGenerator credentialsGenerator;

    @PostMapping
    public String registerSite(@Valid @RequestBody Site site) {
        if (siteService.findBySite(site.getSite().toLowerCase()).isPresent()) {
            return "registration : false";
        }

        var credentials = Arrays.toString(credentialsGenerator.generate()
                .entrySet().toArray()).split("=");

        site.setPassword(credentials[0]);
        site.setLogin(credentials[1]);

        var siteNameToLowerCase = site.getSite().toLowerCase();
        site.setSite(siteNameToLowerCase);
        siteService.save(site);
        return "registration : true" + ", login: " + site.getLogin() + " password: " + site.getPassword();
    }
}
