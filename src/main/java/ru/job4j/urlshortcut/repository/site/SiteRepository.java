package ru.job4j.urlshortcut.repository.site;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.urlshortcut.model.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {
}
