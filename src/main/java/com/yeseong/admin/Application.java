package com.yeseong.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA auditing 활성화

/* @SpringBootApplication
// 1.springboot의 자동화기능 활성화
// 2.패키지 내 application컴포넌트가 어디에 위치해있는지 검사(빈 검색)
// 3.빈에 대해서 Context에 추가하거나 특정 클래스를 참조 할 수 있다.
// spring boot의 자동 설정, spring bean읽기와 생성을 모두 자송으로 설정함.
// spring boot 프로젝트의 Main이라고 할 수 있음. 항상 프로젝트 최상단에 위치해야한다.
*/
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // run으로 내장 Web Applicaion Server 실행, Tomcat을 실행 할 필요 없어짐
        SpringApplication.run(Application.class, args);
    }
}
