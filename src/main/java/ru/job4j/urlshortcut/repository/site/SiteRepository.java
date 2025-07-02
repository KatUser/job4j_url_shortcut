package ru.job4j.urlshortcut.repository.site;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.urlshortcut.model.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {

    Site findBySite(@Param("site") String site);
}
