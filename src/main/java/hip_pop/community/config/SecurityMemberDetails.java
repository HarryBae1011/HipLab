/*
package hip_pop.community.config;

import hip_pop.community.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityMemberDetails implements UserDetails {

    //private final Member member;

    private final Long id;
    private final String username;
    private final String password;
    private final String role;

    public SecurityMemberDetails(Member member) {
        this.id = member.getId();  // ID는 추후 사용 가능
        this.username = member.getName();
        this.password = member.getPassword();
        this.role = member.getMemberRole().getValue(); // 예: "ROLE_USER"
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
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
 */