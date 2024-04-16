package com.myShop.config;

import com.myShop.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // 설정 클래스라고 알려준다
@EnableWebSecurity // Security 를 설정하기 위한 어노테이션
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Bean // 메소드에서 리턴되는 SecurityFilterChain 을 bean 으로 만들어준다.
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 메소드의 매개변수에 HttpSecurity 의 참조값이 전달되는데 해당 객체를 이용해서 설정을 한다음
        String[] whiteList = {"/swagger", "/api-docs/**", "/swagger-ui/**"
                , "/member/login", "/member/post-member"};
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(config ->
                        config
                                .requestMatchers(whiteList).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // 설정된 정보대로 SecurityFilterChain 객체를 만들어서 반환한다
        return httpSecurity.build();
    }

    // 비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //인증매니저
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()).and().build();
    }


}
