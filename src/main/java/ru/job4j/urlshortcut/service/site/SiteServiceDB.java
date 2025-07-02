package ru.job4j.urlshortcut.service.site;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.site.SiteRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SiteServiceDB implements SiteService {

    private final SiteRepository siteRepository;

    @Override
    public Site save(Site site) {
        return siteRepository.save(site);
    }

    @Override
    public Optional<Site> findById(Long id) {
        return siteRepository.findById(id);
    }

    @Override
    public Optional<Site> findBySite(String name) {
        return Optional.ofNullable(siteRepository.findBySite(name));
    }
}
