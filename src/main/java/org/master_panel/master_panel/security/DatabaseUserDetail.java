package org.master_panel.master_panel.security;

import java.util.HashSet;
import java.util.Set;

import org.master_panel.master_panel.model.Role;
import org.master_panel.master_panel.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetail implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public DatabaseUserDetail(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();

        this.authorities = new HashSet<GrantedAuthority>();

        for (Role userRole : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }
    }

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
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

    public Set<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

}
