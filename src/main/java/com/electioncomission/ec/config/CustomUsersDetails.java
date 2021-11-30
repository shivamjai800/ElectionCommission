package com.electioncomission.ec.config;

import com.electioncomission.ec.entity.Users;
import com.sun.tools.javac.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUsersDetails implements UserDetails {

    private Users user;

    public CustomUsersDetails(Users user) {
        super();
        this.user = user;
    }

    public String getRoles()
    {
        return this.user.getUserRole();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority =new SimpleGrantedAuthority(this.user.getUserRole());
        return List.of(simpleGrantedAuthority);
    }


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
