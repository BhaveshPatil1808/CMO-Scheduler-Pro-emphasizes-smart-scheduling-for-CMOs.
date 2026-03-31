package com.Bhavesh.main.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.Bhavesh.main.Service.CustomUserDetailsService;
import com.Bhavesh.main.Configuration.CustomSuccessHandler;

@Configuration
public class Config {

    private final CustomSuccessHandler customSuccessHandler;

    // 🔥 Constructor Injection (recommended)
    public Config(CustomSuccessHandler customSuccessHandler) {
        this.customSuccessHandler = customSuccessHandler;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(CustomUserDetailsService service) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider(service);
//        auth.setUserDetailsService();
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth

        		.requestMatchers("/", "/login", "/users/register", "/users/save").permitAll()

            // 🔒 Role-based access
            .requestMatchers("/cm/**").hasRole("CM")
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/users/**").hasRole("USER")
            .requestMatchers("/meetings/request").hasRole("USER")
            .requestMatchers("/meetings/save").hasRole("USER")
            // 🔥 FIXED (single clean rule)
            .requestMatchers("/meetings/**").hasAnyRole("ADMIN", "CM")

            // Public routes
            
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .successHandler(customSuccessHandler)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        );

        return http.build();
    }
}