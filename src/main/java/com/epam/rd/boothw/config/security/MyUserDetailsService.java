package com.epam.rd.boothw.config.security;

import com.epam.rd.boothw.entity.User;
import com.epam.rd.boothw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void createDefaultUser() {
        String defaultUserName = "user";
        User defaultUSer = userRepository.findByUsername(defaultUserName);
        if (defaultUSer == null)
            userRepository.save(new User(null, defaultUserName, "password"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}