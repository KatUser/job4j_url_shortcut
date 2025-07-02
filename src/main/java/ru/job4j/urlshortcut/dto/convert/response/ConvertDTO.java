package ru.job4j.urlshortcut.dto.convert.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertDTO {
    private HttpStatus status;
    private String message;
}