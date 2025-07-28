package ru.job4j.urlshortcut.statistics.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class StatisticsRequestDto {
    @NotBlank(message = "адрес сайта не может быть пустым")
    private String url;
}
