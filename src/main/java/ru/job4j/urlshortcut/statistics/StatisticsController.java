package ru.job4j.urlshortcut.statistics;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.repository.user.UserRepository;
import ru.job4j.urlshortcut.statistics.dto.StatisticsRequestDto;
import ru.job4j.urlshortcut.statistics.dto.StatisticsResponseDto;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class StatisticsController {

    private UserRepository userRepository;

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponseDto> getUrlStatistics(
            @Valid @RequestBody StatisticsRequestDto statisticsRequestDto) {
        var url = statisticsRequestDto.getUrl();
//        var total = userRepository.findUserBySite(url).get().getCount();
//        return ResponseEntity.ok(new StatisticsResponseDto(
//                url, total
        return null;

    }
}
