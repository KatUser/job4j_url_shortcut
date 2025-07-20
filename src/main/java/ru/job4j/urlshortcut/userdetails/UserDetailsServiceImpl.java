package ru.job4j.urlshortcut.userdetails;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.job4j.urlshortcut.repository.user.UserRepository;

@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var site = userRepository.findUserByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with login: " + login));
        return UserDetailsImpl.build(site);
    }

}
