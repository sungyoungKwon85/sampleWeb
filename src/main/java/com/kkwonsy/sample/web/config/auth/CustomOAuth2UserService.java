package com.kkwonsy.sample.web.config.auth;

import java.util.Collections;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kkwonsy.sample.web.config.auth.dto.OAuthAttributes;
import com.kkwonsy.sample.web.config.auth.dto.SessionUser;
import com.kkwonsy.sample.web.domain.user.User;
import com.kkwonsy.sample.web.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행중인 서비스 구분 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 로그인 진행시 키가 되는 필드값 (=Primary key)
        // 구글은 기본 제공, 네이버/카카오는 기본없음
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
            .getUserNameAttributeName();

        // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담은 dto
        // 네이버 등에서도 사용할 것
        OAuthAttributes attributes = OAuthAttributes
            .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 저장하거나 업데이트함
        User user = saveOrUpdate(attributes);

        // SessionUser: 세션에 사용자 정보를 저장하기 위한 dto
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
            , attributes.getAttributes()
            , attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
            .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
            .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
