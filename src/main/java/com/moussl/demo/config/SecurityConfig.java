package com.moussl.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
            PasswordEncoder passwordEncoder = passwordEncoder();
        return new InMemoryUserDetailsManager(User.withUsername("user1")
                .password(  passwordEncoder.encode("1234") ).roles("USER").build(),
                User.withUsername( passwordEncoder.encode("user2") ).password("1234").roles("ADMIN").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("ADMIN", "USER").build()

        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //return http.formLogin(Customizer.withDefaults()).build();
        return http.formLogin(Customizer.withDefaults()).authorizeHttpRequests(ar -> ar.requestMatchers("/save/**", "/delete/**").hasRole("ADMIN")).authorizeHttpRequests(ar -> ar.anyRequest().authenticated()).build();
        //return http.formLogin(fl->fl.loginPage("/login")).build();
    }

}