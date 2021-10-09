package maciej.grochowski.favorite_dishes.security;

import maciej.grochowski.favorite_dishes.entity.Gourmet;
import maciej.grochowski.favorite_dishes.entity.GourmetRating;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private String email;
    private String password;
    private String name;
    private final List<GrantedAuthority> grantedAuthorityList;

    public MyUserDetails(Gourmet gourmet) {
        this.email = gourmet.getEmail();
        this.password = gourmet.getPassword();
        this.name = gourmet.getName();
        this.grantedAuthorityList = gourmet.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
