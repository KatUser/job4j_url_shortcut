package ru.job4j.urlshortcut.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.authorization.request.SignupRequestDTO;
import ru.job4j.urlshortcut.dto.authorization.response.RegisterDTO;
import ru.job4j.urlshortcut.generator.CredentialsGenerator;
import ru.job4j.urlshortcut.model.ERole;
import ru.job4j.urlshortcut.model.Role;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.repository.role.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.urlshortcut.repository.user.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private PasswordEncoder encoder;

    private final CredentialsGenerator credentialsGenerator;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public RegisterDTO register(SignupRequestDTO signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsBySite(signUpRequest.getSite()))
        ) {
            return new RegisterDTO(HttpStatus.BAD_REQUEST, "registration : false");
        }

        var credentials = credentialsGenerator.generate();

        var user = new User();

        var log = credentials.entrySet().iterator().next().getKey();
        var pass = String.valueOf(credentials.entrySet().iterator().next().getValue());

        user.setLogin(String.valueOf(log));
        user.setPassword(encoder.encode(pass));

        var siteNameToLowerCase = signUpRequest.getSite().toLowerCase();
        user.setSite(siteNameToLowerCase);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        Supplier<RuntimeException> supplier = () -> new RuntimeException("Error: Role is not found.");

        if (strRoles == null) {
            roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(supplier));
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(supplier));
                    case "mod" -> roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(supplier));
                    default -> roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(supplier));
                }
            });
        }
        user.setRole(roles);
        userRepository.save(user);
        return new RegisterDTO(HttpStatus.OK, String.format(
                "registration : true, login : %s, password : %s", log,
                pass));
    }

}
