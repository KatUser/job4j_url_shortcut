package ru.job4j.urlshortcut.dto.authorization.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDTO {
    @NotBlank
    private String message;
}
