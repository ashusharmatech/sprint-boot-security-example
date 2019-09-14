package com.ms.api.springsecurity.service;

import com.ms.api.springsecurity.model.MyUserDetail;
import com.ms.api.springsecurity.model.User;
import com.ms.api.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<User> user = userRepository.findByUserName(username);
        System.out.println(user.get().toString());
        user.orElseThrow(() -> {
            return new UsernameNotFoundException("Not found " + username);
        });
        return user.map(MyUserDetail::new).get();
    }
}
