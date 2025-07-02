package ru.job4j.urlshortcut.dto.userregistration.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;
}