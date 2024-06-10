package com.example.airlines.Service.SecurityService;

import com.example.airlines.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class SecurityUser implements UserDetails {

    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean isActive;
    private final String nickname;

    public SecurityUser(
        String email,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        boolean isActive,
        String nickname
    ) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
        this.nickname = nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user) {
        return new SecurityUser(
            user.getEmail(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
            true,
            user.getNickname()
        );
    }
}

