package ru.job4j.urlshortcut.controller.converter;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import ru.job4j.urlshortcut.siteutils.SiteExtractor;
import ru.job4j.urlshortcut.converter.SiteConverter;
import ru.job4j.urlshortcut.dto.convert.request.ConvertRequestDTO;
import ru.job4j.urlshortcut.dto.convert.response.MessageConvertResponseDTO;
import ru.job4j.urlshortcut.userdetails.UserDetailsImpl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ConverterController {

    @Autowired
    private final SiteConverter siteConverter;

    @PostMapping("/convert")
    public ResponseEntity<MessageConvertResponseDTO> convertSiteUrl(
            @Valid @RequestBody ConvertRequestDTO convertRequestDTO,
            @AuthenticationPrincipal UserDetailsImpl user) {

        /* Достаём url из запроса  */
        var siteInConvertRequestDTO = convertRequestDTO.getUrl();

        /* У урла отрезаем http(s), приводим к нижнему регистру */
        var extractedSite = SiteExtractor.extractSite(siteInConvertRequestDTO);

        /* У урла отрезаем хвост */
        var siteMain = extractedSite.split("/")[0];

        if (!Objects.equals(siteMain, user.getSite())) {
            return ResponseEntity.badRequest().body(new MessageConvertResponseDTO(
                    "You can only convert urls for " + user.getSite()
            ));
        }

        var code = URLEncoder.encode(extractedSite, StandardCharsets.UTF_8);
        return ResponseEntity.ok(new MessageConvertResponseDTO(code));
    }
}
