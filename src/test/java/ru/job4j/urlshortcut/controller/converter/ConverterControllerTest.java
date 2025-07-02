package ru.job4j.urlshortcut.controller.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.urlshortcut.siteutils.SiteConverter;
import ru.job4j.urlshortcut.dto.converter.ConvertRequestDTO;
import ru.job4j.urlshortcut.userdetails.UserDetailsImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConverterControllerTest {

    @Autowired
    private ConverterController converterController;

    @Mock
    private UserDetailsImpl user = Mockito.mock(UserDetailsImpl.class);

    @Autowired
    private SiteConverter siteConverter;

    @Test
    void whenUserConvertsSiteFromAuthTokenHeGetsConvertedUrl() throws Exception {
        var testUrl = "testsite.com&page=1";
        var encryptedUrl = siteConverter.encrypt(testUrl);
        when(user.getSite()).thenReturn("testsite.com");
        var result
                = converterController.convertSiteUrl(new ConvertRequestDTO(testUrl), user);

        assertTrue(result.getBody().toString().contains(encryptedUrl));
        assertTrue(result.getStatusCode().is2xxSuccessful());
    }

    @Test
    void whenUserConvertsSiteNotFromAuthTokenHeGetsWarnMsg() throws Exception {
        var testUrl = "testsite.com&page=1";
        var encryptedUrl = siteConverter.encrypt(testUrl);
        when(user.getSite()).thenReturn("summercat.com");
        var result
                = converterController.convertSiteUrl(new ConvertRequestDTO(testUrl), user);

        assertFalse(result.getBody().toString().contains(encryptedUrl));
        assertTrue(result
                .getBody()
                .toString()
                .contains("Вы можете конвертировать ссылки только для " + "summercat.com"));
        assertTrue(result.getStatusCode().is4xxClientError());
    }
}