package ru.job4j.urlshortcut.dto.authorization.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String site;
    private List<String> roles;

    public JwtResponseDTO(String accessToken, Long id, String username, String site, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.site = site;
        this.roles = roles;
    }
}