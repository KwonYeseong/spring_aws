package com.yeseong.admin;

import com.yeseong.admin.config.auth.SecurityConfig;
import com.yeseong.admin.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
1.JUnit에 내장된 실행자 외 다른 실행자를 실행시킴, 여기서는 SpringRunner
2.springBootTest와 JUnit 사이에 연결자 역할을 한다.
 */
@RunWith(SpringRunner.class)
/*
1.일반적으로 사용하는 MVC테스트용 어노테이션
2.MockMvc에 @Autowired하면 해당 객체를 통해 MVC테스트 가능
*/
@WebMvcTest(controllers = HelloController.class,
            excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
            }
)
public class HelloControllerTest {

    @Autowired // spring이 관리하는 Bean을 주입받는다.
    private MockMvc mvc; // 이 클래스를 통해 HTTP get, post등에 대한 api를 테스트 할 수 있음.

    @Test
    @WithMockUser(roles = "USER")
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // HTTP Header의 status를 검증
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증
    }

    @Test
    @WithMockUser(roles = "USER")
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // API테스트를 할 때 사용될 요청 파라미터를 설정, 값은 Spring만 허용됨
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                // jsonPath : JSON응답값을 필드별로 검증할 수 있는 메소드
                // $을 기준으로 필드명을 암시함
    }
}
