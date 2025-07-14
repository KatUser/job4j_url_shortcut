package ru.job4j.urlshortcut.repository.role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.urlshortcut.model.ERole;
import ru.job4j.urlshortcut.model.Role;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.save(new Role(1L, ERole.ROLE_USER));
        repository.save(new Role(2L, ERole.ROLE_ADMIN));
        repository.save(new Role(3L, ERole.ROLE_MODERATOR));
    }

    @Test
    public void whenRoleExists_thenRoleIsReturned() {
        assertTrue(repository.existsById(1L));
    }

    @Test
    public void whenRoleExists_ThenCanFindRoleByName() {
        var role = repository.findByName(ERole.ROLE_USER).get();
        assertThat(role).isEqualTo(new Role(1L, ERole.ROLE_USER));
    }

    @Test
    public void whenRoleIsDeleted_thenItIsNotFoundByName() {
        repository.deleteByName(ERole.ROLE_USER);
        assertFalse(repository.existsById(1L));
    }

}