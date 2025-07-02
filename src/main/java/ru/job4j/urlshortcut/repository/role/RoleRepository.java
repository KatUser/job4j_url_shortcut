package ru.job4j.urlshortcut.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.ERole;
import ru.job4j.urlshortcut.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

    @Transactional
    void deleteByName(ERole name);
}
