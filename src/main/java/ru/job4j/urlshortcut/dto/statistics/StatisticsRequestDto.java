package ru.job4j.urlshortcut.dto.statistics;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class StatisticsRequestDto {
    @NotBlank(message = "адрес сайта не может быть пустым")
    private String url;
}
