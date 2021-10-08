package maciej.grochowski.favorite_dishes.security;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.entity.Gourmet;
import maciej.grochowski.favorite_dishes.repository.GourmetRepository;
import maciej.grochowski.favorite_dishes.exception.UserDoesNotExist;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final GourmetRepository gourmetRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Gourmet gourmet = gourmetRepository
                .findGourmetByEmail(email)
                .orElseThrow(() -> new UserDoesNotExist(String.format("User with email %s doesn't exist.", email)));

        boolean enabled = true;
        boolean accountNotExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNotLocked = true;

        return new User(
                gourmet.getName(),
                gourmet.getPassword(),
                enabled,
                accountNotExpired,
                credentialsNotExpired,
                accountNotLocked,
                getAuthorityList(gourmet.getRoles())
        );
    }

    private static List<GrantedAuthority> getAuthorityList(List<String> rolesList) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : rolesList) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
