package com.kkwonsy.sample.web.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.kkwonsy.sample.web.domain.user.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity // security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable() // h2-console 화면 사용을 위해 disable함
            .and()
            .authorizeRequests() // URL 별 권한 관리 시작
            .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 전체 권한
            .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER 권한만 줌
            .anyRequest().authenticated() // 나머지 URL은 로그인한 사용자만 권한을 줌
            .and()
            .logout().logoutSuccessUrl("/")
            .and()
            .oauth2Login() // OAuth2 로그인 기능 시작
            .userInfoEndpoint().userService(customOAuth2UserService); // 후속조치를 진행할 UserService 구현체를 등록
    }
}

// csrf:
// (Cross Site Request Forgery : 사이트 간 요청 위조)
// 웹 사이트의 취약점을 이용하여 이용자가 의도하지 하지 않은 요청을 통한 공격
// http 통신의 Stateless 특성을 이용하여 쿠키 정보만 이용해서 사용자가 의도하지 않은 다양한 공격들을 시도
// 로그인한 상태로 https://xxxx.com/logout URL을 호출하게 유도하면 실제 사용자는 의도하지 않은 로그아웃을..
// 가장 간단한 해결책으로는 CSRF Token 정보를 Header 정보에 포함하여 서버 요청을 시도하는 것
// 여기서는 생략..
// .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())