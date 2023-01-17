package com.example.springproject.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.springproject.security.SecurityRoles.*;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();

        var john = User.withUsername("john")
                .password("john")
                .roles(ROLE_ADMIN)
                .build();

        var emma = User.withUsername("emma")
                .password("emma")
                .roles(EMPLOYEE_ADMIN)
                .build();

        var willam = User.withUsername("willam")
                .password("willam")
                .roles(DEPARTMENT_CREATE, DEPARTMENT_PAGE_VIEW, DEPARTMENT_READ)
                .build();

        var lucas = User.withUsername("lucas")
                .password("lucas")
                .roles(CUSTOMER_READ, CUSTOMER_PAGE_VIEW)
                .build();

        var tom = User.withUsername("tom")
                .password("tom")
                .roles()
                .build();

        uds.createUser(john);
        uds.createUser(emma);
        uds.createUser(willam);
        uds.createUser(lucas);
        uds.createUser(tom);

        return uds;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Throwable {
        http.authorizeHttpRequests()
                .requestMatchers("/", "/home")
                .permitAll()
                .anyRequest()
                .authenticated();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
