package com.wilkins.showcase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/actuator",
                                "/actuator/*",
                                "/togglz-console",
                                "/togglz-console/**",
                                "/togglz*",
                                "/togglz**"
                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout(LogoutConfigurer::permitAll)
                .build();
    }
}
