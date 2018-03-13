package com.epam.rd.boothw.config.security;

import com.epam.rd.boothw.entity.User;
import com.epam.rd.boothw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void createDefaultUser() {
        String defaultUserName = "user";
        String defaultPassword = "password";
        User defaultUSer = userRepository.findByUsername(defaultUserName);
        if (defaultUSer == null)
            userRepository.save(new User(null, defaultUserName, encoder.encode(defaultPassword)));
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