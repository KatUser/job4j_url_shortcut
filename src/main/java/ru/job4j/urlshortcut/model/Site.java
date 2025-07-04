package ru.job4j.urlshortcut.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "site")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    String site;

    String login;

    String password;

}
