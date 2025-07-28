package ru.job4j.urlshortcut.statistics.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatisticsResponseDto {

    @NotBlank
    private String url;

    @NotBlank
    private int total;
}
