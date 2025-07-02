package ru.job4j.urlshortcut.converter;

import org.springframework.stereotype.Component;
import ru.job4j.urlshortcut.dto.convert.request.ConvertRequestDTO;
import ru.job4j.urlshortcut.dto.convert.response.ConvertDTO;

@Component
public class SiteConverter {

    public ConvertDTO convert(ConvertRequestDTO convertRequestDTO) {

        return new ConvertDTO();
    }


//    @Override
//    public ConvertDTO convert(ConvertRequestDTO convertRequestDTO) {
//        return ConvertDTO;
//    }

}
