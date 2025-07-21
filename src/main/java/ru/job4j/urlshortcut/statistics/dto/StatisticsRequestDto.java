package ru.job4j.urlshortcut.statistics.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class StatisticsRequestDto {

    private String url;
}
