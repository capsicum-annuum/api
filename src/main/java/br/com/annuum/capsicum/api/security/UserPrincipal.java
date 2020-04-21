package br.com.annuum.capsicum.api.security;

import br.com.annuum.capsicum.api.domain.User;
import br.com.annuum.capsicum.api.domain.enums.Profile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "profile"})
public class UserPrincipal implements UserDetails, User {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Boolean enabled;

    private Profile profile;

    public static UserPrincipal create(final User user) {
        return new UserPrincipal()
                .setEnabled(true)
                .setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setProfile(user.getProfile());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Profile getProfile() {
        return profile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new GrantedAuthorityMapper().map(profile.getRoles());
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}