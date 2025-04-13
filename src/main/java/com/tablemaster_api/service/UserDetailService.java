package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.UserRepository;
import com.tablemaster_api.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new User(user.getId(),
                username,
                user.getEmail(),
                user.getPassword(),
                user.getVerificationCode(),
                user.getVerificationExpiresAt(),
                user.isEnabled());
    }
}
