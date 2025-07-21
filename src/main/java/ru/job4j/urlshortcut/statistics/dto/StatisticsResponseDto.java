package ru.job4j.urlshortcut.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatisticsResponseDto {

    private String url;

    private int total;
}
