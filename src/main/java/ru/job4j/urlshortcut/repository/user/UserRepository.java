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

    Optional<User> findUserBySite(String site);

    Optional<User> findUserById(Long userId);

}
