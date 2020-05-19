package com.yeseong.admin.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 메소드 인자로 세션값을 바로 받을 수 있도록 하기위해 만드는 어노테이션
 * 이 어노테이션이 생성될 수 있는 위치를 지정
 * parameter로 지정했기 때문에 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}

// @interface : 이 파일을 어노테이션 클래스로 지정함
// LoginUser라는 어노테이션이 생성되었다고 보면 된다.