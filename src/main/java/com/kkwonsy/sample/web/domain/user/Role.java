package com.kkwonsy.sample.web.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "guest"), // Spring security에서는 권한 코드에 항상 'ROLE_'이 앞에 있어야 함.
    USER("ROLE_USER", "user");

    private final String key;
    private final String title;
}
