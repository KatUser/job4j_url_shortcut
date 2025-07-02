package ru.job4j.urlshortcut.controller.converter;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.converter.ConverterUtil;
import ru.job4j.urlshortcut.converter.SiteConverter;
import ru.job4j.urlshortcut.dto.convert.request.ConvertRequestDTO;
import ru.job4j.urlshortcut.dto.convert.response.MessageConvertResponseDTO;

@AllArgsConstructor
@RestController
@RequestMapping("/api/")
public class ConverterController {

    @Autowired
    private final SiteConverter siteConverter;

    @Autowired
    private final ConverterUtil converterUtil;

    @PostMapping("/convert")
    public ResponseEntity<MessageConvertResponseDTO> convertSiteUrl(@Valid @RequestBody ConvertRequestDTO convertRequestDTO) {
        var code = converterUtil.generateCode();
        return ResponseEntity.ok(new MessageConvertResponseDTO(code));
    }
}
