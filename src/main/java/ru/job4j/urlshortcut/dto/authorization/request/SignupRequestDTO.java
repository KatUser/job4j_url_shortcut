package ru.job4j.urlshortcut.dto.authorization.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {
    @NotBlank(message = "сайт не может быть пустым")
    @Length(min = 3,
            message = "сайт должен содержать хотя бы три символа")
    private String site;

    private Set<String> role;

}