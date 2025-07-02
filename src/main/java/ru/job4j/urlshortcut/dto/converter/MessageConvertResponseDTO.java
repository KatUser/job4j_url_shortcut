package ru.job4j.urlshortcut.dto.converter;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageConvertResponseDTO {
    @NotBlank
    private String code;
}
