package ru.job4j.urlshortcut.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "called_url")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalledUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

    private String url;

    private Long count;

    public CalledUrl(User user, String url) {
        this.user = user;
        this.url = url;
        this.count = 1L;
    }
}
