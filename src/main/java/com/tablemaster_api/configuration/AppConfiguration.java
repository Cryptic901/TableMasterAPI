package com.tablemaster_api.configuration;

import com.tablemaster_api.abstraction.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfiguration {

    private final UserRepository userRepository;

    public AppConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Bean
//    UserDetailsService userDetailsService() {
//        //TODO
//    }
//
//    @Bean
//    AuthenticationManager authenticationManager() {
//        //TODO
//    }
//
//    @Bean
//    BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    AuthenticationProvider authenticationProvider() {
//        //TODO
//    }
}
