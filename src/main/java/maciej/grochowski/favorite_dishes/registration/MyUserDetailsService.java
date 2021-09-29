package maciej.grochowski.favorite_dishes.registration;

import lombok.AllArgsConstructor;
import maciej.grochowski.favorite_dishes.gourmet.Gourmet;
import maciej.grochowski.favorite_dishes.gourmet.GourmetRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final GourmetRepository gourmetRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Gourmet gourmet = gourmetRepository
                .findGourmetByGourmetEmail(email)
                .orElseThrow(() -> new UserDoesNotExist(String.format("User with email %s doesn't exist.", email)));

        boolean enabled = true;
        boolean accountNotExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNotLocked = true;

        return new User(
                gourmet.getGourmetName(),
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
