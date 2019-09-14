package com.ms.api.springsecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetail implements UserDetails {
    private String userName;
    private String password;
    private List<GrantedAuthority> authorityList;

    public MyUserDetail(User user) {
        System.out.println(user);
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.authorityList = Arrays.asList(user.getRoles().split(",")).stream()
                .map(
                SimpleGrantedAuthority::new).collect(Collectors.toList());

    }

    public MyUserDetail(String userName) {
        this.userName = userName;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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

    @Override
    public String toString() {
        return "MyUserDetail{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authorityList=" + authorityList +
                '}';
    }
}
