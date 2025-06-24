package ru.job4j.urlshortcut.service.site;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.Site;

import java.util.Optional;

public interface SiteService {

    @Transactional
    Site save(Site site);

    Optional<Site> findById(Long id);
}
