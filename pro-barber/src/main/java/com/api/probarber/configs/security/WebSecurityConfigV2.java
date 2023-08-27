package com.api.probarber.configs.security;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigV2 {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/services/**").permitAll()
                .antMatchers(HttpMethod.GET, "/client/**").permitAll()
                .antMatchers(HttpMethod.POST, "/appointment").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/client").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/services").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/security").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/security").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/loyalty-plan").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/loyalty-plan/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/barber").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/barber/**").permitAll()
                .antMatchers(HttpMethod.POST, "/barber").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/client").permitAll()
                .antMatchers(HttpMethod.DELETE, "/services/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and().cors().and()
                .csrf()
                .disable()
                ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://127.0.0.1:8080", "http://localhost:3000", "http://127.0.0.1:3000"));
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("POST","GET"));
        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(List.of("Authorization"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "content-type", "x-requested-with", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "x-auth-token", "x-app-id", "Origin","Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
