package com.yeseong.admin.config.auth;


import com.yeseong.admin.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화시켜줌.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점. 이게 있어야 antMatchers를 할 수있음
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest() // 설정된 값들 이외의 나머지 URL을 나타냄.
                .authenticated()
                .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 성공 시 '/'주소로 이동
                .and()
                .oauth2Login() // OAuth2 로그인기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() // OAuth2로그인 성공 이후 사용자 정보를 가져올때의 설정들을 담당.
                .userService(customOAuth2UserService); // 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록 | 구글, 네이버 로그인을 한 후 추가로 진행하고자하는 기능을 명시할 수 있다.
    }
}
