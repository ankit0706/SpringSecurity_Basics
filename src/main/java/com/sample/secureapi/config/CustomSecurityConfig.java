package com.sample.secureapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class CustomSecurityConfig {
    @Value("${spring.security.user.name}")
    String username;
    @Value("${spring.security.user.password}")
    String password;

    /**
     * Here we are telling that any request going to the endpoints /t20i/home are allowed to go without authentication
     * In other words, it's a free or public endpoint. A request to any other endpoint will need authentication
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                                .requestMatchers("/t20i/home").permitAll()
                                .anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username(username)
                .password(password)
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }


}
