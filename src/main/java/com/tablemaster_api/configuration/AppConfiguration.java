package com.tablemaster_api.configuration;

import com.tablemaster_api.abstraction.repository.UserRepository;
import org.springframework.context.annotation.Configuration;

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
