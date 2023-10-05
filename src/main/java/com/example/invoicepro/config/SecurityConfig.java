//package com.example.invoicepro.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
////         return http.
////                csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(auth->
////                        auth.requestMatchers("/user/**")
////                                .authenticated()
////                                .anyRequest().permitAll())
////                .httpBasic(Customizer.withDefaults())
////                .build();
////
////    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    /**
//     * for injecting BCryptPasswordEncoder encoder in UserRepository
//     *
//     * */
//
//}
