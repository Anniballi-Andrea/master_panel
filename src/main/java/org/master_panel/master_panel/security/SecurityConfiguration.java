package org.master_panel.master_panel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/", "/home").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/monsters/create", "/monsters/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/monsters/**").hasAuthority("ADMIN")
                .requestMatchers("/trait", "/trait/**").hasAuthority("ADMIN")
                .requestMatchers("/actions", "/actions/**").hasAuthority("ADMIN")
                .requestMatchers("/monsters", "/monsters/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/**").permitAll())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailService());

        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}