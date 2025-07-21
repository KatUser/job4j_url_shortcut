package ru.job4j.urlshortcut.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("delete from User as user where user.id = :userId")
    int delete(@Param("userId") Long userId);

    Boolean existsBySite(String name);

    Optional<User> findUserByLogin(String login);

    @Transactional
    @Modifying
    @Query(value = "insert into app_user (id, site, login, password, count)"
    + "values (:#{#user.id}, :#{#user.site}, :#{#user.login}, :#{#user.password}, 0)"
    + "on conflict(site) do update set count = app_user.count + 1 where app_user.site = :#{#user.site}",
            nativeQuery = true)
    void saveOrUpdate(@Param("user") User user);

    Optional<User> findUserBySite(String site);
}
