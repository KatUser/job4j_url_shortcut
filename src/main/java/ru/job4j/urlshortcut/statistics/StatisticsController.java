package ru.job4j.urlshortcut.statistics;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class StatisticsController {

    @GetMapping("/statistics")
    public String getUrlStatistics(@RequestBody String url) {
        return null;
    }
}
