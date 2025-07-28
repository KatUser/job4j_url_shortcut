package ru.job4j.urlshortcut.controller.converter;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import ru.job4j.urlshortcut.controller.converter.convert.SiteConverter;

import ru.job4j.urlshortcut.controller.converter.convert.request.ConvertRequestDTO;
import ru.job4j.urlshortcut.controller.converter.convert.response.MessageConvertResponseDTO;
import ru.job4j.urlshortcut.userdetails.UserDetailsImpl;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ConverterController {

    @PostMapping("/convert")
    public ResponseEntity<MessageConvertResponseDTO> convertSiteUrl(
            @Valid @RequestBody ConvertRequestDTO convertRequestDTO,
            @AuthenticationPrincipal UserDetailsImpl user) throws Exception {

        var siteInConvertRequestDTO = convertRequestDTO.getUrl();

        if (!siteInConvertRequestDTO.contains(user.getSite())) {
            return ResponseEntity.badRequest().body(new MessageConvertResponseDTO(
                    "You can only convert urls for " + user.getSite()
            ));
        }

        var code = SiteConverter.encrypt(siteInConvertRequestDTO);
        return ResponseEntity.ok(new MessageConvertResponseDTO(code));
    }
}
