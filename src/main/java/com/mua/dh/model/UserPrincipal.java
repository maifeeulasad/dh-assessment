package com.mua.dh.model;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
public class UserPrincipal implements UserDetails {
    private LoginCredential loginCredential;

    public UserPrincipal(LoginCredential loginCredential) {
        this.loginCredential = loginCredential;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(loginCredential.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return loginCredential.getPassword();
    }
    @Override
    public String getUsername() {
        return loginCredential.getUsername();
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