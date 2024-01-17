package com.wilkins.showcase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth ->
                        {
                            try {
                                auth
                                        .requestMatchers(AntPathRequestMatcher.antMatcher("/login.html")).permitAll()
                                        .anyRequest().authenticated()
                                        .and()
                                        .oauth2Login()
                                        .loginPage("/login.html")
                                        .defaultSuccessUrl("/greetings", true);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .httpBasic(withDefaults());
        return httpSecurity.build();
    }
}
