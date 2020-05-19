package com.yeseong.admin.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "손님"), //권한코드는 항상 ROLE_로 시작해야한다.
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
