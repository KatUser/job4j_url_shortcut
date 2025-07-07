package ru.job4j.urlshortcut.dto.authorization.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank
    private String login;

    @NotBlank
    private String password;
}