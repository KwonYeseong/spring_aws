package com.yeseong.admin.config.auth;

import com.yeseong.admin.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

/*
 * HandlerMethodArgumentResolver : 컨트롤러 메서드에서 특정 조건에 맞는 파라미터가 있을 때 원하는 값을 바인딩해주는 인터페이스.
 * ex) Controller에서 @RequestBody를 사용해서 Request의 Body 값을 받아올 때, @PathVariable을 사용해서 Path Parameter값을 받아 올 때.
 */
@RequiredArgsConstructor
@Component // 개발자가 직접 작성한 Class를 Bean으로 등록하기 위한 어노테이션. Bean의 의존성 주입은 @Autowired를 이용해 할 수 있다.
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession httpSession;

    /*
     * 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단.
     * 여기서는 파라미터에 @LoginUser 어노테이션이 붙어 있고, 파라미터 클래스 타입이 SesstionUser.class인 경구 true를 반환.
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    /*
     * 파라미터에 전달할 객체를 생성함.
     * 여기서는 세션에서 객체를 가져옴.
     */
    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
