package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;


    @Bean
    public SecurityFilterChain publicSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .securityMatchers()
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(manager -> manager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize ->
                        {
                            try {
                                authorize
                                        .requestMatchers(HttpMethod.GET, "/app/v1/accounts/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/app/v1/accounts/**").permitAll()
                                        .requestMatchers(HttpMethod.PUT, "/app/v1/accounts/**").permitAll()
                                        .requestMatchers(HttpMethod.DELETE, "/app/v1/accounts/**").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/**").authenticated()
                                        .requestMatchers(HttpMethod.POST, "/**").authenticated()
                                        .requestMatchers(HttpMethod.PUT, "/**").authenticated()
                                        .requestMatchers(HttpMethod.DELETE, "/**").authenticated()
                                        .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http.headers(header -> header.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*")));
        http.cors(Customizer.withDefaults());
        return http.build();
    }


    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        List<String> allowedMethods = new ArrayList<String>();
        allowedMethods.add("*");
        List<String> allowedHeaders = new ArrayList<String>();
        allowedHeaders.add("*");
        List<String> exposedHeaders = new ArrayList<String>();
        exposedHeaders.add("*");

        configuration.setAllowedOrigins(allowedMethods);
        configuration.setAllowedMethods(allowedMethods);
        configuration.setAllowCredentials(false);
        configuration.setAllowedHeaders(allowedHeaders);
        configuration.setExposedHeaders(exposedHeaders);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}