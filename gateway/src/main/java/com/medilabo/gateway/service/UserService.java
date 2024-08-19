package com.medilabo.gateway.service;

import com.medilabo.gateway.model.CustomUser;
import com.medilabo.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomUser save(CustomUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public CustomUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
