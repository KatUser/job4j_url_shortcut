package ru.job4j.urlshortcut.repository.calledurl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.CalledUrl;

import java.util.Optional;

@Repository
@Component
public interface CalledUrlRepository extends JpaRepository<CalledUrl, Long> {

    boolean existsByUrl(String url);

    @Async
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = """
            update called_url
                        set count = called_url.count + 1
                        where called_url.url = :calledUrl
            """, nativeQuery = true)
    void updateByUrl(@Param("calledUrl") String calledUrl);

    Optional<CalledUrl> findByUrl(String calledUrl);
}




