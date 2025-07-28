package ru.job4j.urlshortcut.dto.authorization.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "поле login не может быть пустым")
    private String login;

    @NotBlank(message = "поле password не может быть пустым")
    private String password;
}