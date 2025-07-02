package ru.job4j.urlshortcut.controller.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.urlshortcut.dto.authorization.request.SignupRequestDTO;
import ru.job4j.urlshortcut.model.ERole;
import ru.job4j.urlshortcut.model.Role;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.repository.role.RoleRepository;
import ru.job4j.urlshortcut.repository.user.UserRepository;

import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        roleRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.save(new Role(1L, ERole.ROLE_USER));
        userRepository.save(new User(null, "www.registered.com", "login", "password", null));

    }


    @Test
    void whenRegisterWithRoleThenReceiveCredentials() {
        SignupRequestDTO signUpRequest = new SignupRequestDTO(
                "www.testsite.com",
                Set.of("role_user")
        );
        assertTrue(authController.registerUser(signUpRequest).getStatusCode().is2xxSuccessful());
        assertFalse(Objects.requireNonNull(authController.registerUser(signUpRequest).getBody()).getMessage().isEmpty());
    }

    @Test
    void whenRegisterWithoutRoleThenReceiveCredentials() {
        SignupRequestDTO signUpRequest = new SignupRequestDTO(
                "www.testsite.com",
                Set.of()
        );
        assertTrue(authController.registerUser(signUpRequest).getStatusCode().is2xxSuccessful());
        assertFalse(Objects.requireNonNull(authController.registerUser(signUpRequest).getBody()).getMessage().isEmpty());

    }

    @Test
    void whenCannotRegisterTwiceThenReceiveBadRequest() {
        SignupRequestDTO signUpRequest = new SignupRequestDTO(
                "www.registered.com",
                Set.of()
        );
        assertTrue(authController.registerUser(signUpRequest).getStatusCode().is4xxClientError());
        assertFalse(Objects.requireNonNull(authController.registerUser(signUpRequest).getBody()).getMessage().isEmpty());
    }
}