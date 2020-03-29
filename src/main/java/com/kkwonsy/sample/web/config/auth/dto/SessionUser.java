package com.kkwonsy.sample.web.config.auth.dto;

import java.io.Serializable;

import com.kkwonsy.sample.web.domain.user.User;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    // 세션에 SessionUser가 아닌 User를 저장하려고 하면 Failed to convert from type ..Object to byte가 발생함
    // User Entity에 직렬화를 구현안했기 떄문임
    // Entity에 직렬화를 하면?
    // Entity는 언제 다른 Entity와 관계가 있을지 모르기 때문에
    // 직렬화를 하게 되면 다른 관계의 Entity까지 포함되니 성능이슈, 부수효과가 발생할 확률이 높으므로
    // 직렬화를 한 dto를 두는게 바람직함

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        name = user.getName();
        email = user.getEmail();
        picture = user.getPicture();
    }
}
