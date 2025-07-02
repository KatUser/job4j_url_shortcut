package ru.job4j.urlshortcut.dto.statistics;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatisticsResponseDto {

    @NotBlank
    private String url;

    @NotBlank
    private Long total;
}
