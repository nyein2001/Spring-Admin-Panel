package com.example.springproject.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import static com.example.springproject.security.SecurityRoles.*;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private RoleHierarchy roleHierarchy;

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
    public DefaultWebSecurityExpressionHandler expressionHandler(){
        DefaultWebSecurityExpressionHandler handler =
                new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

        return handler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Throwable{

        http.authorizeRequests()
                .expressionHandler(expressionHandler())
                .requestMatchers("/","home")
                .permitAll()
                .requestMatchers("/customers/**")
                .hasRole(CUSTOMER_PAGE_VIEW)
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);
        return handler;
    }

}