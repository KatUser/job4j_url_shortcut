package ru.job4j.urlshortcut.dto.converter;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertRequestDTO {

    @NotBlank(message = "адрес сайта не может быть пустым")
    @Length(min = 3, message = "сайт должен содержать хотя бы три символа")
    private String url;
}