
package com.springboot.userregiauth.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests((auth)->
                auth
                    .requestMatchers("/register").permitAll()
                    .requestMatchers(HttpMethod.GET, "/logout").permitAll()
                    .requestMatchers(HttpMethod.GET, "/Success").permitAll()
                    .anyRequest().authenticated()
            )
            .csrf(csrf->
                csrf
                    .disable()
            )
            .formLogin((form)->
                form
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/home")
                    .permitAll()
            )
            .logout((logout)->
                logout
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .permitAll()
            );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}