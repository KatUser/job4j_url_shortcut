package ru.job4j.urlshortcut.dto.authorization.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {
    @NotBlank
    @Size(min = 5)
    private String site;

    private Set<String> role;
}