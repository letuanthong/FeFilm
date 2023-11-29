package com.devking.fefilm.config;

import com.devking.fefilm.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(bCryptPasswordEncoder().encode("adminPass")).roles("ADMIN")
//                .and()
//                .withUser("user").password(bCryptPasswordEncoder().encode("userPass")).roles("USER");
//    }

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/", "/register", "/login").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
//                        .requestMatchers("/movies/**").hasAuthority("ADMIN")
                        .requestMatchers("/movies/**").hasAuthority("USER")
                        .anyRequest().authenticated())
                .formLogin(login->login.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/",true)
                        .failureUrl("/login?error= true"))
                .logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/login"));
        return http.build();
    }


    @Bean
    WebSecurityCustomizer webSecurityCustomizer(){
        return (web)->web.ignoring().requestMatchers("/resource/**",
                "/static/**",
                "/images/**",
                "/css/**");
    }
}
