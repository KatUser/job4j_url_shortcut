package ru.job4j.urlshortcut.controller.converter;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import ru.job4j.urlshortcut.siteutils.SiteConverter;

import ru.job4j.urlshortcut.dto.converter.ConvertRequestDTO;
import ru.job4j.urlshortcut.dto.converter.MessageConvertResponseDTO;
import ru.job4j.urlshortcut.userdetails.UserDetailsImpl;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ConverterController {

    @Autowired
    private SiteConverter siteConverter;

    @PostMapping("/convert")
    public ResponseEntity<MessageConvertResponseDTO> convertSiteUrl(
            @Valid @RequestBody ConvertRequestDTO convertRequestDTO,
            @AuthenticationPrincipal UserDetailsImpl user) throws Exception {

        var siteInConvertRequestDTO = convertRequestDTO.getUrl();

        if (!siteInConvertRequestDTO.contains(user.getSite())) {
            return ResponseEntity.badRequest().body(new MessageConvertResponseDTO(
                    "Вы можете конвертировать ссылки только для " + user.getSite()
            ));
        }

        var code = siteConverter.encrypt(siteInConvertRequestDTO);

        return ResponseEntity.ok(new MessageConvertResponseDTO(code));
    }
}
