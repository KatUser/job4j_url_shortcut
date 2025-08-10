package ru.job4j.urlshortcut.repository.role;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.urlshortcut.model.ERole;
import ru.job4j.urlshortcut.model.Role;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(new Role(null, ERole.ROLE_USER));
        repository.save(new Role(null, ERole.ROLE_ADMIN));
        repository.save(new Role(null, ERole.ROLE_MODERATOR));
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    @Order(1)
    @Disabled
    public void whenRoleExists_thenRoleIsReturned() {
        assertTrue(repository.existsById(1L));
    }

    @Test
    @Order(2)
    public void whenRoleExists_ThenCanFindRoleByName() {
        var role = repository.findByName(ERole.ROLE_ADMIN).get();
        assertThat(role).isEqualTo(new Role(2L, ERole.ROLE_ADMIN));
    }

    @Test
    @Order(3)
    public void whenRoleIsDeletedByName_thenItDoesntExist() {
        repository.deleteByName(ERole.ROLE_USER);
        assertFalse(repository.existsById(1L));
    }

    @Test
    @Order(4)
    public void whenRoleIsDeletedById_thenItDoesNotExist() {
        repository.deleteById(1L);
        assertFalse(repository.existsById(1L));
    }

    @Test
    @Order(5)
    public void whenCannotFindRoleByName_ThenReceiveEmptyResult() {
        repository.deleteAll();
        assertTrue(repository.findByName(ERole.ROLE_MODERATOR).isEmpty());
    }

    @Test
    @Order(6)
    public void whenCannotFindRoleById_ThenReceiveEmptyResult() {
        assertTrue(repository.findById(100L).isEmpty());
    }

    @Test
    @Order(7)
    public void whenCannotAllRoles_ThenReceiveEmptyResult() {
        repository.deleteAll();
        assertTrue(repository.findAll().isEmpty());
    }

}