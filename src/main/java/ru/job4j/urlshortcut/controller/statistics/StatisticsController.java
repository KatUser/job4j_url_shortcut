package ru.job4j.urlshortcut.controller.statistics;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.repository.calledurl.CalledUrlRepository;
import ru.job4j.urlshortcut.dto.statistics.StatisticsRequestDto;
import ru.job4j.urlshortcut.dto.statistics.StatisticsResponseDto;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class StatisticsController {

    @Autowired
    private CalledUrlRepository calledUrlRepository;

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponseDto> getUrlStatistics(
            @Valid @RequestBody StatisticsRequestDto statisticsRequestDto) {
        var url = statisticsRequestDto.getUrl();

        var foundRecordsByUrl = calledUrlRepository
                .findByUrl(url);

        if (foundRecordsByUrl.isEmpty()) {
            return ResponseEntity.ok(new StatisticsResponseDto(
                    url, 0L));
        }

        var totalCalls = foundRecordsByUrl.get().getCount();

        return ResponseEntity.ok(new StatisticsResponseDto(
                url, totalCalls));

    }
}
